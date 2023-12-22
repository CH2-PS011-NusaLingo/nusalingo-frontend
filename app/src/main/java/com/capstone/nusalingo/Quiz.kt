package com.capstone.nusalingo

object Quiz {
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"

    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        val que1 = Question(
            1,
            "Bahasa sundanya rumah adalah",
            "Imah/Bumi",
            "suhun",
            "gubuk",
            "bangunan",
            1
        )
        val que2 = Question(
            1,
            "Memperkenalkan diri dalam bahasa sunda",
            "Namaku Wahyu",
            "Nami Abdi Wahyu",
            "Jenengan wahyu",
            "Kaula",
            2
        )
        val que3 = Question(
            1,
            "Bahasa sundanya mata",
            "mata",
            "soca",
            "rarai",
            "sirah",
            2
        )
        val que4 = Question(
            1,
            "bahasa sundanya lusa",
            "teu nyaho",
            "kamari",
            "isukan",
            "pageto",
            4
        )
        val que5 = Question(
            1,
            "Kecap pananya nyaeta",
            "kalimat untuk bertanya",
            "kalimat untuk pana",
            "kalimat untuk menjawab",
            "tanda tanya",
            1
        )

        questionsList.add(que1)
        questionsList.add(que2)
        questionsList.add(que3)
        questionsList.add(que4)
        questionsList.add(que5)

        return questionsList
    }
}