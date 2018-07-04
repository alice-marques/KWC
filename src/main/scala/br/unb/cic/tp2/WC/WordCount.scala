package br.unb.cic.tp2.WC

import scala.collection.immutable.ListMap
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object WordCount {
  def readFile(func:(String,    (ListBuffer[String], (ListBuffer[String],(ListBuffer[String],(mutable.Map[String, Int],(ListMap[String, Int],()=>Unit)=>Unit)=>Any)=>Any)=>Any)=>Any)=>Any) ={
    val sentences = scala.io.Source.fromFile("Texto.txt").getLines().mkString
    func(sentences, splitWords)
  }
  def filterChars(book:String, func: (ListBuffer[String], (ListBuffer[String],(ListBuffer[String], (mutable.Map[String, Int], (ListMap[String, Int],()=>Unit)=>Unit)=>Any)=>Any)=>Any)=>Any)={
    val chars = """,.:"<>';-&""".toSet
    val filteredList = new ListBuffer[String]
    filteredList += book.filterNot(chars)
    func(filteredList, removeStopWords)
  }
  def splitWords(lista:ListBuffer[String], func:(ListBuffer[String], (scala.collection.mutable.ListBuffer[String], (scala.collection.mutable.Map[String,Int], (scala.collection.immutable.ListMap[String,Int], () => Unit)=>Unit) => Any) => Any)=>Any)={
    val splitList = lista.head.split(" ").toList
    func(splitList.to[ListBuffer], countWords)
  }
  def removeStopWords(lista: ListBuffer[String], func:(ListBuffer[String], (mutable.Map[String,Int], (ListMap[String,Int], () => Unit)=>Unit) => Any) => Any)={
    val listNoStop = new ListBuffer[String]
    val stopWords = scala.io.Source.fromFile("stopWords.txt").getLines().toList
    for(word <- lista;if stopWords.contains(word.toLowerCase) )lista.remove(lista.indexOf(word))
    func(lista, sort)
  }
  def countWords(lista: ListBuffer[String], func:(mutable.Map[String, Int], (ListMap[String, Int],()=>Unit)=>Unit)=>Any)={
    val freq = scala.collection.mutable.Map[String ,Int]()
    for(word <- lista){
      if(freq.contains(word.toLowerCase()))freq(word.toLowerCase())+=1
      if(!freq.contains(word.toLowerCase()))freq += (word.toLowerCase() -> 1)
    }
    func(freq, printText)
  }
  def sort(map: mutable.Map[String, Int], func:(ListMap[String, Int], ()=>Unit)=> Unit)={
    val sortedWords: ListMap[String, Int] = ListMap(map.toSeq.sortWith(_._2 > _._2):_*)
    func(sortedWords, noOp)
  }
  def printText(sortedWords: ListMap[String, Int], func:()=>Unit): Unit={
    sortedWords.foreach(println)
    func()
  }
  def noOp():Unit={}
}
