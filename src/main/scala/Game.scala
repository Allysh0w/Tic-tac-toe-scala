import BoardControl._

object Game extends App {

  def handlePlayerChoice(player: Player): (Int, Int) = {
    println(s"${player.nickname}, please make your choice! Remember your SYMBOL = ${player.symbol}")
    println("You need to choose a row and a column (row, col). For example: 1,2")
    val input = io.StdIn.readLine()

    val regex = """^(\d+),(\d+)$""".r

    input match {
      case regex(r,c) if r.forall(_.isDigit) && c.forall(_.isDigit) =>
        (r.toInt,c.toInt)
      case _ =>
        handlePlayerChoice(player)
    }
  }

  def gameLoop(board: List[List[String]],
               currentPlayer: Player, // player1 always will go first at the beginning of the game
               player1: Player,
               player2: Player,
               log: String => Unit): Unit = {

    println(drawBoard(board))
    printDivisor
    val (row, col) = handlePlayerChoice(currentPlayer)

    val updatedBoard = updateBoard(board, row, col, currentPlayer)
    updatedBoard match {
      case Right(boardUpdated) =>
        val currentPlayerWin = checkIfPlayerWin(boardUpdated, currentPlayer)

        if(currentPlayerWin) {
          log(s"Congratulations, ${currentPlayer.nickname}!! Yow won the game!")
          log("Check the board below!")
          log(drawBoard(boardUpdated))
        } else if (checkDraw(boardUpdated, player1, player2)) {
          log("It's a draw!")
          log("Check the board below!")
          log(drawBoard(boardUpdated))
          log(s"Congratulations ${player1.nickname} and ${player2.nickname}!")
        } else {
          val swapTo = if(currentPlayer == player1) player2 else player1
          gameLoop(
            boardUpdated,
            swapTo,
            player1,
            player2,
            log)
        }

      case Left(errorMessage) =>
        printDivisor
        println(errorMessage)
        gameLoop(
          board,
          currentPlayer,
          player1,
          player2,
          log)
    }
  }

  def printDivisor:Unit =
    println("=" * 60)


  private def InitializeGame() {
    val insertPlayerName: () => String = () => io.StdIn.readLine()
    val log: String => Unit = s => println(s)

    log("Hello to Tic-Tac-Toe game!")
    log("Insert the name for the player 1")
    val player1Nickname = insertPlayerName()
    val player1 = Player(nickname = player1Nickname, symbol = "X")
    log(s"Welcome ${player1.nickname}!! Your symbol for this game will be '${player1.symbol}' ")

    printDivisor

    log("Insert the name for the player 2")
    val player2Nickname = insertPlayerName()
    val player2 = Player(nickname = player2Nickname, symbol = "O")
    log(s"Welcome also ${player2.nickname}!! Your symbol for this game will be  '${player2.symbol}'")

    log("It's time to play!! Good Luck!!")
    log(s"${player1.nickname}, you'll start the match!")

    val initialBoard = boardInit()
    gameLoop(initialBoard, player1, player1, player2, log)
  }

  InitializeGame()

}


