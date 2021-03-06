package com.packt.courseware.l8.modes
import com.packt.courseware.l8.Processed
import com.packt.courseware.l8.modes.StoreRemindCommand.{RemindPattern, StorePattern}

sealed trait StoreRemindCommand

case class StoreCommand(name:String,value:String) extends StoreRemindCommand
case class RemindCommand(name:String) extends StoreRemindCommand

case object StoreRemindCommand extends
{

  val StorePattern = raw"store ([^\W]+) (.*)".r;
  val RemindPattern = raw"remind ([^\W]+)".r;

  val empty: ChatbotMode = processor(Map.empty)

  def processor(state:Map[String,String]): ChatbotMode = ChatbotMode.function{ (line,effects) =>
    line match {
      case StorePattern(n,v) => Some(Processed("ok",processor(state.updated(n,v)),false))
      case RemindPattern(n) if state.isDefinedAt(n) => Some(Processed(state(n),processor(state),false))
      case _ => None
    }
  }

}



