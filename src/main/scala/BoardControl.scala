object BoardControl {

  def boardInit(): List[List[String]] = List.fill(3)(List.fill(3)(" ")) // can be char as well

  def drawBoard(board: List[List[String]]): String = {
    board.map(_.map{
      case " " => "*"
      case symbol@_ => symbol
    }.mkString(" | "))
      .mkString("\n")
  }

  def checkRows(board: List[List[String]], playerSymbol: String): Boolean =
    board.exists(_.forall(_ == playerSymbol))

  def checkColumns(board: List[List[String]], playerSymbol: String): Boolean =
    board.transpose.exists(_.forall(_ == playerSymbol))

  def checkDiagonals(board: List[List[String]], playerSymbol: String): Boolean = {
    val rightDiagonal = board
      .indices
      .map(x => board(x)(board.size - 1 - x))
      .forall(_ == playerSymbol)

    val leftDiagonal = board
      .indices
      .map(x => board(x)(x))
      .forall(_ == playerSymbol)

    rightDiagonal || leftDiagonal
  }

  def checkIfPlayerWin(board: List[List[String]], player: Player): Boolean =
    checkRows(board, player.symbol) || checkColumns(board, player.symbol) || checkDiagonals(board, player.symbol)


  def checkDraw(board: List[List[String]],
                player1: Player,
                player2: Player): Boolean =
    board.flatten.forall(x => x == player1.symbol || x == player2.symbol)


  def updateBoard(board: List[List[String]],
                 row: Int,
                 column: Int,
                  player: Player): Either[String, List[List[String]]] =
    board.lift(row).flatMap(_.lift(column)) match {
      case Some(" ") =>
        Right(board.updated(row, board(row).updated(column, player.symbol)))
      case _ =>
        Left("The cell is not available. Try to choose again!")
    }

}
