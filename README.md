# ♟ Chess Game (Java + Spring Boot)

This project is a fully functional chess game implemented in **Java** using **Spring Boot**. It supports standard chess rules including legal moves, piece capturing, turn-based play, and basic game flow.

---

## 🚀 Features

- Full chessboard setup with all 32 pieces
- Legal movement validation for:
  - King
  - Queen
  - Rook
  - Bishop
  - Knight
  - Pawn (including first-move and capturing)
- Turn-based enforcement (White then Black)
- Capturing logic
- Game-over detection (King captured)
- Move history with notation
- REST API endpoints
- Game reset functionality

---

## 🔧 Technologies Used

- Java 17
- Spring Boot
- Maven
- RESTful API

---

## 📁 Project Structure

com.chess
├── controller # REST controllers (ChessController, GlobalExceptionHandler)
├── model # All chess models (Board, Piece, Match, Player, etc.)
├── service # GameService abstraction
└── ChessApplication.java # Main entry point

yaml
Copier le code

---

## 🧪 API Endpoints

| Endpoint | Method | Description |
|---------|--------|-------------|
| `/api/chess/board` | GET | Returns the current state of the board |
| `/api/chess/move` | POST | Makes a move (`fromRow`, `fromCol`, `toRow`, `toCol`) |
| `/api/chess/reset` | POST | Resets the game |
| `/api/chess/history` | GET | Lists all past moves in notation format |

---

## 🧠 How the Logic Works

Each chess piece inherits from an abstract `Piece` class and overrides `isValidMove(...)` to validate its moves. The central logic lives in the `Match` class, which:
- Controls turn management
- Records move history
- Detects illegal or self-capturing moves
- Updates the board after a valid move

---

## 📚 Learning Objectives for My Friends

- Understand OOP principles through inheritance and polymorphism (Piece hierarchy)
- Practice RESTful design using Spring Boot
- Structure Java projects using MVC (Model-View-Controller)
- Learn about game state management and validation
- Apply exception handling using custom exceptions and global handlers

---

## ✅ Next Steps (Suggested Enhancements)

- Implement **Check** and **Checkmate** detection
- Add **Castling**, **En passant**, and **Pawn Promotion**
- Build a **Frontend UI** (e.g. using React, Angular, or JavaFX)
- Add a **multiplayer mode** with persistent user sessions

---

## 🧑‍💻 Author

**Melek MARAOUI**  
Feel free to contribute, fork, or use this project for learning purposes.

📬 Contact: [melek7.maraoui@gmail.com](mailto:melek7.maraoui@gmail.com)

---

Enjoy playing chess with your own backend!