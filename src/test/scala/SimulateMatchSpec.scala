import org.scalatest.wordspec.AnyWordSpecLike

import BoardControl._
import Fixtures._

class SimulateMatchSpec extends AnyWordSpecLike {

  "Simulate a game" must {
    "Match and player 1 must win" in {

      val boardExpected: List[List[String]] = List(
        List("X", " ", "O"),
        List(" ", "X", " "),
        List(" ", "O", "X")
      )

      val initialBoard: List[List[String]] = boardInit()
      val board1: List[List[String]] = updateBoard(initialBoard, 0,0, player = player1)
        .fold(_ => initialBoard, t => t)

      val board2: List[List[String]] = updateBoard(board1, 0, 2, player = player2)
        .fold(_ => initialBoard, t => t)

      val board3: List[List[String]] = updateBoard(board2, 1, 1, player = player1)
        .fold(_ => initialBoard, t => t)

      val board4: List[List[String]] = updateBoard(board3, 2, 1, player = player2)
        .fold(_ => initialBoard, t => t)

      val board5: List[List[String]] = updateBoard(board4, 2, 2, player = player1)
        .fold(_ => initialBoard, t => t)

      println(board5)

      assert(checkIfPlayerWin(board5, player1))
      assert(board5 == boardExpected)

    }


    "Match and player 2 must win" in {

      val boardExpected: List[List[String]] = List(
        List("X", "X", "O"),
        List(" ", "X", "O"),
        List(" ", " ", "O")
      )

      val initialBoard: List[List[String]] = boardInit()
      val board1: List[List[String]] = updateBoard(initialBoard, 0, 0, player = player1)
        .fold(_ => initialBoard, t => t)

      val board2: List[List[String]] = updateBoard(board1, 0, 2, player = player2)
        .fold(_ => initialBoard, t => t)

      val board3: List[List[String]] = updateBoard(board2, 1, 1, player = player1)
        .fold(_ => initialBoard, t => t)

      val board4: List[List[String]] = updateBoard(board3, 2, 2, player = player2)
        .fold(_ => initialBoard, t => t)

      val board5: List[List[String]] = updateBoard(board4, 0, 1, player = player1)
        .fold(_ => initialBoard, t => t)

      val board6: List[List[String]] = updateBoard(board5, 1, 2, player = player2)
        .fold(_ => initialBoard, t => t)


      assert(checkIfPlayerWin(board6, player2))
      assert(board6 == boardExpected)

    }

    "Work and the match must be draw" in {

      val boardExpected: List[List[String]] = List(
        List("X", "O", "X"),
        List("X", "X", "O"),
        List("O", "X", "O")
      )

      val initialBoard: List[List[String]] = boardInit()
      val board1: List[List[String]] = updateBoard(initialBoard, 0, 0, player = player1)
        .fold(_ => initialBoard, t => t)

      val board2: List[List[String]] = updateBoard(board1, 0, 1, player = player2)
        .fold(_ => initialBoard, t => t)

      val board3: List[List[String]] = updateBoard(board2, 0, 2, player = player1)
        .fold(_ => initialBoard, t => t)

      val board4: List[List[String]] = updateBoard(board3, 1, 2, player = player2)
        .fold(_ => initialBoard, t => t)

      val board5: List[List[String]] = updateBoard(board4, 1, 0, player = player1)
        .fold(_ => initialBoard, t => t)

      val board6: List[List[String]] = updateBoard(board5, 2, 2, player = player2)
        .fold(_ => initialBoard, t => t)

      val board7: List[List[String]] = updateBoard(board6, 1, 1, player = player1)
        .fold(_ => initialBoard, t => t)

      val board8: List[List[String]] = updateBoard(board7, 2, 0, player = player2)
        .fold(_ => initialBoard, t => t)

      val board9: List[List[String]] = updateBoard(board8, 2, 1, player = player1)
        .fold(_ => initialBoard, t => t)


      assert(!checkIfPlayerWin(board9, player1))
      assert(!checkIfPlayerWin(board9, player1))
      assert(checkDraw(board9, player1, player2))
      assert(board9 == boardExpected)

    }


  }
}
