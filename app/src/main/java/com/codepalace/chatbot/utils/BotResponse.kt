package com.codepalace.chatbot.utils

import androidx.appcompat.app.AppCompatActivity
import com.codepalace.chatbot.utils.Constants.OPEN_CITY

object BotResponse : AppCompatActivity(){

    var iataDep: String = ""
    var iataArr: String = ""
    var persNumber: String = ""


    fun basicResponses(_message: String): String {

        val random = (0..2).random()
        val message =_message.toLowerCase()


        return when {

            message.contains("hello") -> {
                when (random) {
                    0 -> "Amazing! First, let's find the perfect flight. Type the following structure: \"your origin city to your departure city\" (example: Munich to Venice)"
                    1 -> "Excellent! First, let's find the perfect flight. Type the following structure: \"your origin city to your departure city\" (example: Munich to Venice)"
                    2 -> "Great! First, let's find the perfect flight. Type the following structure: \"your origin city to your departure city\" (example: Munich to Venice)"
                    else -> "error" }
            }

            message.contains("to") ->{
                var plecare: String?= message.substringBeforeLast("to")
                var sosire: String?= message.substringAfterLast("to")

                if (plecare != null) {
                    plecare = plecare.dropLast(1)
                    iataDep = plecare.capitalize()
                }
                if (sosire != null) {
                    sosire = sosire.drop(1)
                    iataArr = sosire.capitalize()
                }

                if(iataArr == "Amsterdam")
                    "Wow! Amsterdam is a unique city! Enjoy the historic buildings, beautiful museums and pleasant ambiance. Go for a boat tour on the canals, stroll through Vondelpark, and go shopping in the inner city. When do you want to leave? Use the calendar icon from the bar to select date"

                else
                    if(iataArr == "Barcelona")
                        "Nice! Barcelona sited between the sea and the mountains, has found a formidable balance: a foot in the traditional things and the other in the avant-garde. When do you want to leave? Use the calendar icon from the bar to select date"
                else
                    if(iataArr == "Copenhagen")
                        "Amazing! Copenhagen is known for being the city of fairy tales. Reputed to be happiest city in the world, Copenhagen is known for its canals, excellent food, Tivoli Gardens and for once being home to the children's writer, Hans C Andersen. When do you want to leave? Use the calendar icon from the bar to select date"
                else
                    if(iataArr == "Dublin")
                        "Wow! Framed by mountains, centred on a river and edged by a beautiful bay, Dublin's streets and alleys are filled with vibrant art and historic buildings, hip cafés and traditional \"old man\" pubs, as Dubliners call them. When do you want to leave? Use the calendar icon from the bar to select date"
                else
                    if(iataArr == "Edinburgh")
                        "Thanks to its spectacular rocks, rustic buildings and a huge collection of medieval and classic architecture, including numerous stone decorations, Edinburgh is often considered one of the most lively cities in Europe. When do you want to leave? Use the calendar icon from the bar to select date"
                else
                    if(iataArr == "Gdansk")
                        "Situated on the coast of the Baltic Sea, Gdansk is Poland's principal seaport and one of its biggest tourist destinations. It boasts several important historical attractions, such as the Royal Way, famous promenade street of Polish kings, along with historic cathedrals, medieval ports and oodles of cool cafes. When do you want to leave? Use the calendar icon from the bar to select date"
                else
                    if(iataArr == "Helsinki")
                        "Helsinki delights travelers with its sea-facing landscape, diverse architecture, world-famous design, and Nordic cuisine. Finland's capital is compact enough to explore on foot, and many reputable hotels are in the center of the action. When do you want to leave? Use the calendar icon from the bar to select date"
                else
                    if(iataArr == "Iasi")
                        "Situated in Romania’s province Moldavia, Iasi is one of the country’s oldest cities, and is the most important political, economic and cultural centre in this region. When do you want to leave? Use the calendar icon from the bar to select date"
                else
                    if(iataArr == "Jerusalem")
                        "Jerusalem is the religious and historical epicenter of the world. A surreal and vibrant city, holy to Jews, Muslims, and Christians – over one-third of all the people on the earth. When do you want to leave? Use the calendar icon from the bar to select date"
                else
                    if(iataArr == "Krakow")
                        "Krakow looks like a city straight out of a Disney movie. Cobbled streets lined with horse-pulled carriages, beautiful squares surrounded by pretty buildings and churches, and a huge castle that gives Cinderella’s castle a run for its money. When do you want to leave? Use the calendar icon from the bar to select date"
                else
                    if(iataArr == "London")
                        "London is a modern, innovative, and iconic city, steeped in history. It’s famous for its historic palaces and magnificent landmarks, world-class museums, iconic attractions and events, picturesque parks and gardens. London is known for its vibrant culture and cultural icons, such as red buses, West End shows, Queen Elizabeth II and the royal family, literary figures William Shakespeare and Charles Dickens, and film icons James Bond and Harry Potter. When do you want to leave? Use the calendar icon from the bar to select date"
                else
                    if(iataArr == "Madrid")
                        "Madrid, cheerful and vibrant at all hours, is famous for being an open city with all kinds of people from anywhere in the world. When do you want to leave? Use the calendar icon from the bar to select date"
                else
                    if(iataArr == "Naples")
                        "Naples is one of the most iconic cities in Italy and one of the most popular destinations in the entire Mediterranean region. Its history spans back nearly 3,000 years to the ancient Greeks who founded the Naples area during the 8th century BC. When do you want to leave? Use the calendar icon from the bar to select date"
                else
                    if(iataArr == "Oslo")
                        "The capital of Oslo is one of Europe’s fastest-growing cities. International media is constantly writing about Oslo’s innovative architecture, museums, and neighbourhoods, as well as everything that moves on the food, fashion, art, and music scenes. When do you want to leave? Use the calendar icon from the bar to select date"
                else
                    if(iataArr == "Paris")
                        "Paris is unarguably one of the most beautiful cities in the world, the capital of France, of art and of fashion. There are a thousand things to do and see in Paris and however much time you have here, it won’t be enough: climb to the top of the Eiffel Tower, stroll down the Champs Elysées, visit the Louvre, see many shows and exhibitions, or simply wander along the banks of the Seine...read in French in the Tuileries garden, and quite simply take the time to experience the Parisian way of life! When do you want to leave? Use the calendar icon from the bar to select date"
                else
                    if(iataArr == "Rome")
                        "Rome, the “Eternal City,” brims with ancient history, from the Colosseum to the port of Ostia Antica to majestic Vatican City and the Sistine Chapel. Because of its history, art, architecture, and beauty – and perhaps its gelato and pasta! – Rome is one of our most popular cities. When do you want to leave? Use the calendar icon from the bar to select date"
                else
                    if(iataArr == "Stockholm")
                        "Stockholm is known as one of the most inclusive and welcoming cities in the world. Its contemporary, urban appeal is balanced with centuries-old history and closeness to nature. As for the things to do in Stockholm, the list is endless. When do you want to leave? Use the calendar icon from the bar to select date"
                else
                    if(iataArr == "Timisoara")
                        "Timisoara is one of the most beautiful towns in Romania and is located in the western part of the country. The city gets its charm from its wonderful squares and beautiful buildings, which boast influences from a range of architectural styles, with unique facades and vibrant colours, and also the park with a multitude of species of roses and other plants. When do you want to leave? Use the calendar icon from the bar to select date"
                else
                    if(iataArr == "Vienna")
                        "Austria’s capital Vienna offers a blend of imperial traditions, music, and endearing charm. A city that inspires with the old and the new alike, and always has a cosy place available in a coffee house or wine tavern. When do you want to leave? Use the calendar icon from the bar to select date"
                else
                    if(iataArr == "Warsaw")
                        "Warsaw is a city with a very tumultuous history. Once called “Paris of the East,” this was one of Europe’s most beautiful cities until it was flattened in World War II. Over the past decades, Warsaw has rebuilt itself, rising up from the ashes and emerging once again as one of Europe’s great cities. When do you want to leave? Use the calendar icon from the bar to select date"
                else
                    if(iataArr == "Zagreb")
                        "Zagreb, capital of the Republic of Croatia, is known for its diverse economy, historical museums, cultural events, sporting and governmental hub. When do you want to leave? Use the calendar icon from the bar to select date"
                else
                    "Nice!! I set $iataDep as your origin and $iataArr as your destination. What's your departure date? Use the calendar icon from the bar to select date"

            }

            message.contains("departure") -> {

                "When do you want to return?"
            }

            message.contains("return") -> {

                "How many people are you? Please type x adults"
            }


            message.contains("adults") -> {
                val noP: String?= message.substringBeforeLast(" adults")

                if (noP != null) {
                    persNumber = noP
                }

                "You will be redirected to our partner. Type ok if your agree"
            }



            message.contains("ok") -> {
                OPEN_CITY
            }


            else -> {
                when (random) {
                    0 -> "I don't understand..."
                    1 -> "Try asking me something different"
                    2 -> "Idk"
                    else -> "error"
                }
            }
        }
    }


}