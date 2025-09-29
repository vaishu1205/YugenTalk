# YugenTalk - Japanese Learning Chatbot 🇯🇵📚

## Overview

YugenTalk is an interactive web-based chatbot application designed to help users learn Japanese language through conversational practice. The application provides an engaging, real-time learning environment where users can practice Japanese vocabulary, grammar, and conversation skills with an AI-powered chatbot assistant.

The name "Yugen" (幽玄) is a Japanese aesthetic concept representing profound grace and subtle depth - reflecting the elegant journey of learning the Japanese language.

The platform features a modern, animated user interface with real-time messaging capabilities, user authentication, and persistent chat history to track learning progress.

---

## Tech Stack

### **Frontend**
- **HTML5** - Semantic markup and structure
- **CSS3** - Advanced styling with CSS variables, animations, and transitions
- **Vanilla JavaScript** - Core functionality and DOM manipulation
- **Font Awesome 6.0** - Icon library for UI elements

### **Backend** (Expected Architecture)
- **REST API Server** (Port 8080)
  - Authentication endpoints (`/auth/login`, `/auth/signup`)
  - Chat history endpoint (`/chat/:username`)
- **WebSocket Server** (Port 3000)
  - Real-time bidirectional messaging
  - Live conversation practice

### **Key Technologies**
- **WebSocket API** - Real-time communication protocol
- **Fetch API** - HTTP requests for authentication and data retrieval
- **LocalStorage API** - Theme preference persistence
- **CSS Variables** - Dynamic theming system
- **CSS Animations & Keyframes** - Smooth UI transitions and effects

---

## Project Flow

### **1. Application Initialization**
```
User loads application
    ↓
Theme preference loaded from localStorage
    ↓
Login/Signup screen displayed
    ↓
Animated background and UI elements render
```

### **2. Authentication Flow**

#### **Sign Up Process:**
```
User creates account with username and password
    ↓
Click "Sign Up" button
    ↓
POST request to /auth/signup
    ↓
Success: Account created → Show success message → Auto-switch to Login tab
    ↓
Error: Display error message (username taken, etc.)
```

#### **Login Process:**
```
User enters credentials
    ↓
Click "Login" button
    ↓
POST request to /auth/login
    ↓
Success: Store username → Hide login screen → Show chat interface
    ↓
Error: Display error message (invalid credentials)
```

### **3. Japanese Learning Chat Flow**

#### **Initial Setup:**
```
Successful login
    ↓
Fetch previous learning chat history: GET /chat/:username
    ↓
Display conversation history
    ↓
Establish WebSocket connection to ws://localhost:3000
    ↓
Bot greets user in Japanese: "こんにちは! Welcome to YugenTalk!"
    ↓
User can start learning conversation
```

#### **Learning Conversation Flow:**
```
User types message (English or Japanese)
    ↓
Message displayed in chat (user bubble)
    ↓
Message sent via WebSocket to chatbot backend
    ↓
Typing indicator shown (bot is "thinking")
    ↓
Bot processes message and generates response:
    - Teaches Japanese vocabulary
    - Explains grammar points
    - Provides translations
    - Corrects mistakes
    - Offers practice exercises
    ↓
Bot response received via WebSocket
    ↓
Typing indicator hidden
    ↓
Bot message displayed in chat (bot bubble)
    ↓
Chat scrolls to bottom
    ↓
Conversation continues...
```

### **4. Theme Toggle Flow**
```
User clicks theme toggle button (moon/sun icon)
    ↓
Check current theme
    ↓
Switch theme (light ↔ dark)
    ↓
Update CSS variables
    ↓
Update icon (moon ↔ sun)
    ↓
Save preference to localStorage
    ↓
Smooth transition applied
```

### **5. Logout Flow**
```
User clicks logout button
    ↓
Close WebSocket connection
    ↓
Save learning progress
    ↓
Clear chat display
    ↓
Reset to welcome message
    ↓
Show login/signup screen
    ↓
Clear input fields
```

---

## Key Features

### **🎓 Japanese Learning Features**
- Interactive conversation practice
- Real-time Japanese language assistance
- Vocabulary and grammar explanations
- Translation support (English ↔ Japanese)
- Pronunciation guidance
- Cultural context and usage tips
- Progress tracking through chat history

### **🎨 User Interface**
- Modern, glassmorphism design
- Smooth animations and transitions
- Floating background shapes
- Dark/Light theme toggle with persistence
- Fully responsive design (mobile, tablet, desktop)
- Japanese-inspired visual elements

### **🔐 Authentication**
- User registration (signup)
- Secure login system
- Personalized learning sessions
- Individual chat history per user

### **💬 Chat System**
- Real-time messaging via WebSocket
- Conversation history persistence
- Typing indicators
- Animated message bubbles
- Auto-scroll to latest messages
- User/Bot avatar system
- Japanese character support (Hiragana, Katakana, Kanji)

### **✨ Animations & UX**
- Slide-up card animations
- Message slide-in effects
- Button hover and click effects
- Typing indicator with bouncing dots
- Theme toggle rotation
- Smooth transitions between sections

### **📱 Responsive Design**
- Mobile-first approach
- Adaptive layouts for all screen sizes
- Touch-friendly interface
- Optimized for learning on-the-go

---

## Learning Use Cases

### **Beginner Level:**
- Learn basic greetings (こんにちは, おはよう, etc.)
- Practice Hiragana and Katakana
- Simple sentence construction
- Basic vocabulary (numbers, colors, food, etc.)

### **Intermediate Level:**
- Grammar practice (particles は, が, を, に, で, etc.)
- Verb conjugations
- Polite vs. casual speech
- Common expressions and phrases

### **Advanced Level:**
- Complex sentence structures
- Kanji reading and writing
- Business Japanese
- Cultural nuances and honorifics

---

## Data Flow Architecture

```
┌─────────────────┐
│   YugenTalk UI  │
│   (Frontend)    │
│  Japanese Input │
└────────┬────────┘
         │
         ├─────────────────────────────┐
         │                             │
         ▼                             ▼
┌────────────────┐          ┌──────────────────┐
│  REST API      │          │  WebSocket       │
│  (Port 8080)   │          │  Server          │
│                │          │  (Port 3000)     │
│  - /auth/login │          │                  │
│  - /auth/signup│          │  - Real-time     │
│  - /chat/:user │          │    Japanese      │
└────────────────┘          │    conversation  │
         │                  └──────────────────┘
         │                             │
         └─────────────┬───────────────┘
                       ▼
              ┌─────────────────┐
              │    Database     │
              │  - Users        │
              │  - Chat History │
              │  - Learning     │
              │    Progress     │
              └─────────────────┘
```

---

## Message Format

### **Outgoing (User → Server):**
```json
{
  "text": "User message in English or Japanese",
  "user": "username"
}
```

### **Incoming (Server → User):**
```
Plain text response from Japanese learning bot
(May include Japanese characters, romaji, translations, and explanations)
```

---

## Sample Conversation

```
User: "How do I say 'good morning' in Japanese?"

Bot: "Good morning in Japanese is おはよう (ohayou) for casual situations, 
     or おはようございます (ohayou gozaimasu) for formal situations! 
     
     Try using it in a sentence:
     おはようございます、田中さん！
     (Ohayou gozaimasu, Tanaka-san!)
     'Good morning, Mr./Ms. Tanaka!'"

User: "What's the difference between は and が?"

Bot: "Great question! Both は and が are particles, but they have different uses:
     
     は (wa) - Topic marker
     私は学生です (Watashi wa gakusei desu) - 'I am a student'
     
     が (ga) - Subject marker
     誰が来ますか？(Dare ga kimasu ka?) - 'Who is coming?'
     
     は introduces what you're talking about, while が marks the subject 
     doing the action. Would you like more examples?"
```

---

## Component Structure

```
YugenTalk Application
│
├── Header
│   ├── Logo with Japanese character icon
│   └── Theme toggle button
│
├── Authentication Section
│   ├── Login Form
│   │   ├── Username input
│   │   ├── Password input
│   │   └── Login button (animated)
│   │
│   ├── Signup Form
│   │   ├── Username input
│   │   ├── Password input
│   │   └── Signup button (animated)
│   │
│   └── Status message display
│
└── Japanese Learning Chat Section
    ├── Chat Header
    │   ├── Bot info & online status
    │   └── Logout button
    │
    ├── Messages Container
    │   ├── Welcome message (bilingual)
    │   ├── Learning conversation history
    │   └── User/Bot messages with Japanese support
    │
    └── Input Area
        ├── Message input (supports Japanese IME)
        ├── Send button
        └── Typing indicator
```

---

## Browser Compatibility

- ✅ Chrome 90+ (Best for Japanese input)
- ✅ Firefox 88+
- ✅ Safari 14+
- ✅ Edge 90+
- ✅ Opera 76+

**Requirements:**
- WebSocket support
- Japanese font rendering
- CSS Grid & Flexbox
- CSS Variables
- Fetch API
- LocalStorage API
- Japanese Input Method Editor (IME) support

---

## Future Enhancements (Potential)

- Voice recognition for pronunciation practice
- Kanji drawing recognition
- Spaced repetition flashcard system
- JLPT (Japanese Language Proficiency Test) preparation mode
- Daily vocabulary challenges
- Japanese typing practice mode
- Cultural lessons and stories
- Grammar quiz system
- Furigana (reading aids) toggle
- Export chat history for review
- Progress tracking dashboard
- Achievement badges for learning milestones
