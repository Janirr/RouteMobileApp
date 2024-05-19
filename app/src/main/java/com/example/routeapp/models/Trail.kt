package com.example.routeapp.models

import com.example.routeapp.R

class Trail(
    private var name: String,
    private var description: String,
    private var imageResourceId: Int
) {
    fun getName(): String {
        return name
    }

    fun getDescription(): String {
        return description
    }

    fun getResourceId(): Int {
        return imageResourceId;
    }


    companion object {
        var easyTrails: ArrayList<Trail> = arrayListOf(
            Trail(
                "Jaszczurówka",
                "Skorzystaj z tego 4,7-km szlaku „tam i z powrotem” w pobliżu Zakopane, Małopolskie. Trasa uznawana za łatwy, jej pokonanie zajmuje średnio 1 godz. 26 min.. Bardzo popularny obszar dla turystyka piesza, więc podczas zwiedzania prawdopodobnie spotkasz innych ludzi. Szlak jest otwarty przez cały rok i jest atrakcyjny o każdej porze roku. Psy nie mają wstępu na ten szlak — zostaw swojego pupila w domu.",
                R.drawable.jaszczurowka
            ),
            Trail(
                "Droga pod Reglami",
                "Ta płaska ścieżka biegnie wzdłuż brzegu malowniczego jeziora. Szlak oferuje fantastyczne możliwości obserwacji ptaków i miejsc do odpoczynku przy wodzie. Idealny dla spacerowiczów szukających spokoju przy dźwiękach przyrody. Długość trasy to około 3 km.",
                R.drawable.droga_pod_reglami
            ),
            Trail(
                "Wokół Morskiego Oka",
                "Wypróbuj ten 2,6-km szlak w formie pętli w pobliżu Zakopane, Małopolskie. Trasa uznawana za łatwy, jej pokonanie zajmuje średnio 36 min.. Bardzo popularny obszar dla Turystyka z plecakiem i turystyka piesza, więc podczas zwiedzania prawdopodobnie spotkasz innych ludzi. Szlak jest otwarty przez cały rok i jest atrakcyjny o każdej porze roku. Psy nie mają wstępu na ten szlak — zostaw swojego pupila w domu.",
                R.drawable.morskie_oko
            ),
            Trail(
                "Lysá Poľana",
                "Poznaj ten 6,6-km szlak „tam i z powrotem” w pobliżu Jaworzyna Tatrzańska, Prešovský kraj. Trasa uznawana za łatwy, jej pokonanie zajmuje średnio 1 godz. 25 min.. Popularny szlak dla turystyka piesza, kolarstwo górskie oraz Bieganie, ale w spokojniejszych porach dnia nadal można cieszyć się samotnością.",
                R.drawable.lysa_polana
            ),
            Trail(
                "Zapora Wodna im. Ignacego Mościckiego",
                "Zbadaj ten 5,1-km szlak w formie pętli w pobliżu Jaworze, Śląskie. Trasa uznawana za łatwy, jej pokonanie zajmuje średnio 1 godz. 32 min.. Popularny szlak dla turystyka piesza, Bieganie oraz wycieczka rowerowa, ale w spokojniejszych porach dnia nadal można cieszyć się samotnością.",
                R.drawable.zapora_wodna
            ),
            Trail(
                "Bulwary im. Wiktora Wołkowa",
                "Zbadaj ten 2,4-km szlak „tam i z powrotem” w pobliżu Supraśl, Podlaskie. Trasa uznawana za miarkowanie wymagający, jej pokonanie zajmuje średnio 42 min.. Popularny szlak dla Bieganie, Chodzenie oraz wycieczka rowerowa, ale w spokojniejszych porach dnia nadal można cieszyć się samotnością. Szlak jest otwarty przez cały rok i jest atrakcyjny o każdej porze roku. Psy są mile widziane, ale muszą być trzymane na smyczy.",
                R.drawable.bulwary
            ),
            Trail(
                "Rezerwat Przyrody Jałówka",
                "Sprawdź ten 6,1-km szlak w formie pętli w pobliżu Supraśl, Podlaskie. Trasa uznawana za miarkowanie wymagający, jej pokonanie zajmuje średnio 1 godz. 49 min.. Popularny szlak dla turystyka piesza, ale w spokojniejszych porach dnia nadal można cieszyć się samotnością. Szlak jest otwarty przez cały rok i jest atrakcyjny o każdej porze roku.",
                R.drawable.rezerwat
            )
        )

        var hardTrails: ArrayList<Trail> = arrayListOf(
            Trail(
                "Ścieżka Wysokogórska",
                "Ten wymagający szlak jest przeznaczony dla doświadczonych turystów. Prowadzi przez strome zbocza i skaliste grzbiety, oferując zapierające dech w piersiach widoki na okoliczne szczyty. Szlak wymaga dobrej kondycji fizycznej i odpowiedniego wyposażenia. Długość trasy to około 10 km z dużymi przewyższeniami.",
                R.drawable.hard
            )
        )
    }
}