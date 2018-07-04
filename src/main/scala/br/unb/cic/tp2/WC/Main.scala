package br.unb.cic.tp2.WC

object Main {
  def main(args: Array[String]): Unit = {
    WordCount.readFile(WordCount.filterChars)
  }
}