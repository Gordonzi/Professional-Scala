package com.packt.courseware.l8.modes

import java.time.format.DateTimeFormatter

import com.packt.courseware.l8.{EffectsProvider, Processed, modes}

object CurrentDate extends ChatbotMode {

  override def process(line: String)(implicit effects: EffectsProvider): Option[Processed] =
    line match {
      case "date" =>
        val currentDate = effects.currentDate()
        val message = currentDate.format(DateTimeFormatter.ofPattern("YYYY-MM-dd"))
        Some(Processed(message,this,false))
      case _  => None
    }

}
