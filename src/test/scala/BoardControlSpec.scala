
import BoardControl._
import Fixtures._
//import org.scalatest.funsuite.AnyFunSuite
//import org.scalatest.matchers.must.Matchers.convertToAnyMustWrapper
import org.scalatest.wordspec.AnyWordSpecLike

import scala.Console.in

class BoardControlSpec extends AnyWordSpecLike {


  "Check logic for rows for player 1 and player 2 and the player must Win the match!" must {

    "should return true when a row is completely filled with the player1 symbol" in {
      val board = List(
        List("X", "X", "X"),
        List("O", "O", "X"),
        List("O", "X", "O")
      )
      assert(checkRows(board, "X"))
      assert(checkIfPlayerWin(board, player1))
    }

    "should return true when a row is completely filled with the player2 symbol" in {
      val board = List(
        List("O", "X", "X"),
        List("X", "X", "O"),
        List("O", "O", "O")
      )
      assert(checkRows(board, "O"))
      assert(checkIfPlayerWin(board, player2))
    }

  }

  "Check logic for columns for player 1 and player 2" must {
    "should return true when a column is completely filled with the player1 symbol" in {
      val board = List(
        List("O", "X", "X"),
        List("X", "X", "O"),
        List("O", "X", "O")
      )
      assert(checkColumns(board, "X"))
      assert(checkIfPlayerWin(board, player1))
    }

    "should return true when a column is completely filled with the player2 symbol" in {
      val board = List(
        List("O", "X", "O"),
        List("X", "X", "O"),
        List("X", "O", "O")
      )
      assert(checkColumns(board, "O"))
      assert(checkIfPlayerWin(board, player2))
    }
  }

  "Check logic for diagonals for player 1 and the player must Win the match!" must {

    "should return true when a diagonal is completely filled with the player1 symbol" in {
      val board = List(
        List("X", "O", "X"),
        List("O", "X", "X"),
        List("X", "O", "O")
      )
      assert(checkDiagonals(board, "X"))
      assert(checkIfPlayerWin(board, player1))
    }

    "should return true when a diagonal is completely filled with the player2 symbol" in {
      val board = List(
        List("O", "X", "X"),
        List(" ", "O", "X"),
        List(" ", " ", "O")
      )
      assert(checkDiagonals(board, "O"))
      assert(checkIfPlayerWin(board, player2))
    }

  }

  "Check the logic for draw match" must {
    "should return to for a draw board" in {
      val board = List(
        List("X", "X", "O"),
        List("O", "X", "X"),
        List("X", "O", "O")
      )

      assert(!checkRows(board, "X"))
      assert(!checkRows(board, "O"))
      assert(!checkColumns(board, "O"))
      assert(!checkColumns(board, "X"))
      assert(checkDraw(board, player1, player2))
    }
  }

  "Empty board" must {
    "should return an empty board showing empty cells" in {

      val expectedBoard: List[List[String]] = List(
        List(" ", " ", " "),
        List(" ", " ", " "),
        List(" ", " ", " ")
      )

      val initialBoard: List[List[String]] = boardInit()
      assert(initialBoard == expectedBoard)
    }
  }


  "Check Updates on the board" must {
    "The board must be updated successfully in the space 0,2 for the player1" in {
      val board = List(
        List("O", "X", " "),
        List(" ", "X", "X"),
        List(" ", "O", "O")
      )
      val expectedBoard = List(
        List("O", "X", "X"),
        List(" ", "X", "X"),
        List(" ", "O", "O")
      )
      val rowValue = 0
      val columnValue = 2
      val updatedBoard = updateBoard(board, rowValue, columnValue, player1)

      assert(Right(expectedBoard) == updatedBoard)
    }

    "The board must be updated successfully in the space 1,0 for the player2" in {
      val board = List(
        List("O", "X", "X"),
        List(" ", "X", "X"),
        List(" ", "O", "O")
      )
      val expectedBoard = List(
        List("O", "X", "X"),
        List("O", "X", "X"),
        List(" ", "O", "O")
      )
      val rowValue = 1
      val columnValue = 0
      val updatedBoard = updateBoard(board, rowValue, columnValue, player2)

      assert(Right(expectedBoard) == updatedBoard)
    }

    "The board must not be updated in a cell already occupied for cell (2,2)" in {
      val board = List(
        List("O", "X", "X"),
        List(" ", "X", "X"),
        List(" ", "O", "O")
      )

      val rowValue = 2
      val columnValue = 2
      val updatedBoard = updateBoard(board, rowValue, columnValue, player1)

      assert(updatedBoard == Left("The cell is not available. Try to choose again!"))
    }

  }



}
