<!-- The README-Template was borrowed from this repo: https://github.com/othneildrew/Best-README-Template -->

<!-- Improved compatibility of back to top link: See: https://github.com/othneildrew/Best-README-Template/pull/73 -->
<a id="readme-top"></a>
<!--
*** Thanks for checking out the Best-README-Template. If you have a suggestion
*** that would make this better, please fork the repo and create a pull request
*** or simply open an issue with the tag "enhancement".
*** Don't forget to give the project a star!
*** Thanks again! Now go create something AMAZING! :D
-->


<!-- ABOUT THE PROJECT -->
## About The Project
This is a console-based application that inspects submitted text for complicated words and attempts to replace them with simpler words using Cosine Similarity Search, outputting the processed text into a text file.

This project is partially based on [this project by me](https://github.com/KonopelkoMihass/Similarity-Search-with-Word-Embeddings).


The project was developed as part of the higher diploma course.


### Built With
Java


<!-- GETTING STARTED -->
## Getting Started

### Prerequisites
Java SDK 22.0.1 is required to compile and run this project.


### Running

1. Clone the repo    
	```sh
   git clone https://github.com/KonopelkoMihass/Simplifying-Text-with-Word-Embeddings
   ```
2. Go into the src/ folder
3. Open the console in the directory and compile it
   ```sh
   javac ie/atu/sw/ConsoleColour.java ie/atu/sw/Runner.java
   ```

4. Run it via a following command
   ```sh
   java ie/atu/sw/Runner
   ```


<!-- USAGE EXAMPLES -->
## Usage

Once run, a title screen should appear within the console, followed by the menu with 5 descriptive options, with 1 and 2 stating that they are not set (set: false).

For option 1, load a text file containing GloVe embeddings (with precisely 50 embeddings per word). 
For option 2, load a text with Google 1000 words (a list of words, separated by new lines).
Option 3 allows to alter the location of output file.
Option 4 will start a simplification phase of application once the user submits either a file with text to simplify or would manually type the text. If necessary inputs are not set, program will request user to supply them before running simplification.
Option 5 would quit the application.


<!-- Features -->
## Features

- Option to provide text to simplify either through a file or by typing it out.
- Retention of punctuation and placement of new lines from the input file and their inclusion into the output file.
- Uses Cosine Similarity Search to perform simplification.
- Menu indicates if all files were loaded, and updates when a new input file was added.
- Output location can be altered.
- Javadoc documentation.

<!-- CONTACT -->
## Contact

Mihass Konopelko - [Twitter/X](https://x.com/MihassM60911) - konopelkomihass@gmail.com

Project Link: [https://github.com/KonopelkoMihass/Simplifying-Text-with-Word-Embeddings](https://github.com/KonopelkoMihass/Simplifying-Text-with-Word-Embeddings)

<p align="right">(<a href="#readme-top">back to top</a>)</p>
