# Kingdoms
Projekt jest aplikacją do obsługi prostego modelu, którego można wykorzystać do gry komputerowej. Model implementuje:
- Prowincje - obszary geograficzne, które wchodzą w skład księstwa lub królestwa. Posiadają powierzchnię i nazwę.
- Władców - jednostki kontrolujące dane księstwo, królestwo lub cesarstwo. Władca posiada imię, wiek, renomę, siłę oraz roszczenia (do prowincji, księstw lub królestw)
- Księstwa - zbiór prowincji rządzonych przez władcę. Księstwo posiada poziom stabilności i centralizacji. Dziedziczy właściwości po klasie prowincji
- Królestwa - Wyższy poziom księstwa (dziedziczy po klasie księstwa właściwości). Cechą wyróżniającą królestwo jest poziom prawowitości.
- Cesarstwo - zbiór królestw. Dziedziczy właściwości po klasie królestwa. Posiada współczynnik wpływu (na świat gry).

## Funkcjonalność
Za pomocą aplikacji można utworzyć wyżej wymienione obiekty, zmodyfikować je, usunąć i przeglądać listę obiektów w bazie.
Wszystkie obiekty są dodawane do wspólnej bazy danych (listy obiektów). Każdy obiekt posiada unikalny numer ID.

Poniżej zrzut ekranu z okna głównego aplikacji:
![alt Ekran główny aplikacji. Skecje 'Dodawanie', 'Przeglądanie' i 'Usuwanie'](https://github.com/Zimoslaw/Java-oop-project/blob/main/main.png?raw=true)
Przyciski w sekcji "Dodawanie" otwierają okna dodawania obiektu wybranego typu.
Przycisk "Przeglądarka obiektów" otwiera okno przeglądarki obietów w bazie programu.
Przycisk "Usuń obiekt z bazy" otwiera okno, za pomocą którego można usunąć obiekt o zadanym ID.

Zrzut ekranu z okna przeglądarki obiektów w bazie:
![alt Ekran przeglądarki obiektów w bazie.](https://github.com/Zimoslaw/Java-oop-project/blob/main/browser.png?raw=true)
Obiekty są wyświetlane na liście jednokrotnego wyboru w postaci tekstowej.
Przeglądarka obiektów pozwala na filtrowanie po typie (klasie) obiektów i nazwie obiektów.
Przy zaznaczeniu obiektu z listy i kliknięciu "Modyfikuj", wyświetla się okno modyfikacji parametrów danego obiektu.

**Obiekty są zapisywane tylko w pamięci programu (nie są zapisywane na dysku i przestają istnieć po zamknięciu programu).**
