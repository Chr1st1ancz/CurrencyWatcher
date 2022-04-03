# CurrencyWatcher

#### 02.03.2022
- Merge Otto branch, Abraham, Vít, Poláček


<details>
  <summary>Uživatel databáze</summary>
  <br>
  
| Formát:              | Název proměnný (proměnná)      |
|----------------------|--------------------------------|
| VARCHAR(255)         | E-Mail                         |
| JAVA_OBJECT(HashMap) | Currencies, co uživatel sleduje|
| Pomocí H2 DB         | Odděláno 4 školní hodiny       |
</details>  
  
<details>
  <summary>Currency databáze</summary>
  <br>
  
| Formát:              | Název proměnný (proměnná)      |
|----------------------|--------------------------------|
| VARCHAR(255)?        | Currency                       |
| DOUBLE(?)            | Hodnota vůči CZK               |
| DATE(?)              | Datum, kdy byl převod aktualizovaný|
| Pomocí H2 DB         | Odděláno 0 školních hodin      |
</details>  
  
<details>
  <summary>Getter z APIn</summary>
  <br>
  
| APIny:               | idk                            |
|----------------------|--------------------------------|
| Pomocí bolesti       | Odděláno 0 školních hodin      |
</details>
