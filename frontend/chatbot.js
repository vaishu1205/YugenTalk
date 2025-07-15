require('dotenv').config();
const express = require('express');
const WebSocket = require('ws');
const http = require('http');
const path = require('path');
const axios = require('axios');
const { GoogleGenerativeAI } = require('@google/generative-ai');

// Initialize Gemini with API Key
const genAI = new GoogleGenerativeAI(process.env.GEMINI_API_KEY);

// Use Gemini 2.0 Flash with proper config and system instruction
const model = genAI.getGenerativeModel({
    model: 'gemini-2.0-flash',
    generationConfig: {
        temperature: 0.4,
        maxOutputTokens: 2048,
        topK: 1,
        topP: 1,
    },
    systemInstruction: {
        role: 'system',
        parts: [
            {
                text: `You are YugenTalk, a warm and friendly bilingual chatbot that helps users learn Japanese and feel emotionally supported. 
Give short and kind responses. Speak both in Japanese and English when helpful. 
Explain Japanese words or grammar briefly. Don't be overly lengthy unless asked for.`,
            },
        ],
    },
});

const app = express();
const server = http.createServer(app);
const wss = new WebSocket.Server({ server });

app.use(express.static(path.join(__dirname, 'public')));

console.log('🤖 YugenTalk Chatbot (Gemini 2.0 Flash) is starting...');

wss.on('connection', function connection(ws) {
    ws.send("こんにちは 😊 わたしはYugenTalkです。お気持ちはいかがですか？");

    ws.on('message', async function incoming(message) {
        let parsed;
        try {
            parsed = JSON.parse(message);
        } catch (err) {
            console.error("❌ Invalid message format:", message);
            return;
        }

        const userMessage = parsed.text;
        const userId = parsed.user;

        if (!userId) {
            console.warn("⚠️ Missing user ID");
            return;
        }

        console.log(`📩 Message from ${userId}:`, userMessage);

        let botReply = "申し訳ありません、もう一度言っていただけますか？";


        let botReplyChunks = '';
        try {
            const streamResult = await model.generateContentStream({
                contents: [
                    {
                        role: 'user',
                        parts: [{ text: userMessage }],
                    },
                ],
            });

            for await (const chunk of streamResult.stream) {
                const chunkText = chunk.text();
                botReplyChunks += chunkText;
            }

            botReply = botReplyChunks;
        } catch (err) {
            console.error("❌ Gemini streaming error:", err.message);
        }

        try {
            await axios.post('http://localhost:8080/chat/save', {
                userId: userId,
                message: userMessage,
                sender: 'user',
            });

            await axios.post('http://localhost:8080/chat/save', {
                userId: userId,
                message: botReply.replace(/<br>/g, '\n').replace(/<[^>]*>?/gm, ''),
                sender: 'bot',
            });
        } catch (err) {
            console.error("❌ Error saving to backend:", err.message);
        }

        ws.send(botReply);
    });
});

server.listen(3000, function () {
    console.log('✅ YugenTalk Chatbot (Gemini 2.0 Flash) running at http://localhost:3000');
});
