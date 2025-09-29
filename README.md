# YugenTalk - Emotional Japanese Support Bot 🇯🇵💬

## Overview

YugenTalk is an interactive web-based chatbot application designed to provide emotional support and help users express their feelings in a comfortable, engaging environment. The application features a modern, animated user interface with real-time messaging capabilities, user authentication, and persistent chat history.

The name "Yugen" (幽玄) is a Japanese aesthetic concept representing profound grace and subtle depth - reflecting the bot's purpose of providing thoughtful emotional support.

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
  - Live chat communication

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
User enters username and password
    ↓
Click "Sign Up" button
    ↓
POST request to /auth/signup
    ↓
Success: Show success message → Auto-switch to Login tab
    ↓
Error: Display error message
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
Error: Display error message
```

### **3. Chat Interface Flow**

#### **Initial Setup:**
```
Successful login
    ↓
Fetch chat history: GET /chat/:username
    ↓
Display previous messages
    ↓
Establish WebSocket connection to ws://localhost:3000
    ↓
Show "Welcome back" message
```

#### **Messaging Flow:**
```
User types message and presses Enter (or clicks Send)
    ↓
Message displayed in chat (user bubble)
    ↓
Message sent via WebSocket
    ↓
Typing indicator shown
    ↓
Bot response received via WebSocket
    ↓
Typing indicator hidden
    ↓
Bot message displayed in chat (bot bubble)
    ↓
Chat scrolls to bottom
```

### **4. Theme Toggle Flow**
```
User clicks theme toggle button
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
Clear chat messages
    ↓
Reset to welcome message
    ↓
Show login/signup screen
    ↓
Clear input fields
```

---

## Key Features

### **🎨 User Interface**
- Modern, glassmorphism design
- Smooth animations and transitions
- Floating background shapes
- Dark/Light theme toggle with persistence
- Fully responsive design (mobile, tablet, desktop)

### **🔐 Authentication**
- User registration (signup)
- Secure login system
- Session management
- Error handling with user feedback

### **💬 Chat System**
- Real-time messaging via WebSocket
- Message history persistence
- Typing indicators
- Animated message bubbles
- Auto-scroll to latest messages
- User/Bot avatar system

### **✨ Animations**
- Slide-up card animations
- Message slide-in effects
- Button hover and click effects
- Typing indicator with bouncing dots
- Theme toggle rotation
- Sparkle effects (ready for implementation)

### **📱 Responsive Design**
- Mobile-first approach
- Adaptive layouts for all screen sizes
- Touch-friendly interface
- Optimized spacing and font sizes

---

## Data Flow Architecture

```
┌─────────────────┐
│   YugenTalk UI  │
│   (Frontend)    │
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
│  - /chat/:user │          │    messaging     │
└────────────────┘          └──────────────────┘
         │                             │
         └─────────────┬───────────────┘
                       ▼
              ┌─────────────────┐
              │    Database     │
              │  - Users        │
              │  - Chat History │
              └─────────────────┘
```

---

## Message Format

### **Outgoing (User → Server):**
```json
{
  "text": "User message content",
  "user": "username"
}
```

### **Incoming (Server → User):**
```
Plain text message from bot
```

---

## Component Structure

```
YugenTalk Application
│
├── Header
│   ├── Logo with icon
│   └── Theme toggle button
│
├── Authentication Section
│   ├── Login Form
│   │   ├── Username input
│   │   ├── Password input
│   │   └── Login button
│   │
│   ├── Signup Form
│   │   ├── Username input
│   │   ├── Password input
│   │   └── Signup button
│   │
│   └── Status message display
│
└── Chat Section
    ├── Chat Header
    │   ├── Bot info & status
    │   └── Logout button
    │
    ├── Messages Container
    │   ├── Welcome message
    │   ├── Chat history
    │   └── User/Bot messages
    │
    └── Input Area
        ├── Message input field
        ├── Send button
        └── Typing indicator
```

---

## Browser Compatibility

- ✅ Chrome 90+
- ✅ Firefox 88+
- ✅ Safari 14+
- ✅ Edge 90+
- ✅ Opera 76+

**Requirements:**
- WebSocket support
- CSS Grid & Flexbox
- CSS Variables
- Fetch API
- LocalStorage API

---

## Future Enhancements (Potential)

- End-to-end encryption for messages
- Multi-language support
- Voice message support
- Emoji picker
- Message reactions
- File sharing capability
- Group chat functionality
- Push notifications
- Progressive Web App (PWA) support
