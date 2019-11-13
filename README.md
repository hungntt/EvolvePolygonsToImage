# Evolving Semi-Transparent Polygons into Work of Art
* Frankfurt University of Applied Sciences
* Author: Hung Thanh Truong Nguyen, Son Hai Lam, Dat Quoc Ho
* Supervisor: Prof. Doina Logofatu
* Submitted date: 05 February 2019
* Executable Jar File (.jar): .\out\artifacts\GUI_jar
* Source code: .\src\MonaLisa\
* JavaDoc: .\JavaDoc\index.html

## 1. Problems
By combining multiple layers consisting of colored, semi-transparent, two-dimensional polygons, we can create shapes and colors as images or mimic an artwork with a relatively high precision. This kind of art is known as evolutionary art, a branch of generative art that is instead of being a product of human’s creativity and artistry, a computer system manipulate the shapes and colors to produce the
final images after an iterated process of selection and modification. Surprisingly, evolutionary art has been around since the dawn of computer. In nineteenth century, ”an illusionist created an automata named Zelda that was capable of drawing simple images”. Generally
speaking, computer-related arts can be divided into two categories: (1) ”Systems performing some sort of art understanding task, such as musical analysis, and systems that work as ”intelligent” tools for human artists; (2) constructed artists which ”are supposed to be capable of creating aesthetically meritorious artworks on their own, with minimal human intervention.” One of the more popular approaches to generative art is genetic algorithms. Genetic algorithms, which are the most popular evolutionary algorithms, are adaptive heuristic search algorithm based on the evolutionary ideas of natural selection and genetics (Machado J. 2009). In some way, because genetic algorithms are based on Darwin’s theory of evolution through genetic evolution, they are random, but the logics are probability-based and express a systematic and critical selection. A population of candidate solutions is evolved toward better solutions. Each candidate solution, represents an artificial chromosome, has a set of properties which can be mutated and altered; traditionally, solutions are represented in binary as strings of 0s and 1s, but other encodings are also possible (Korejo I. 2013). In each generation produced, the best and fittest solution (or individual) is selected while others are eliminated. The process of mutating involves parent chromosomes are selected and their genetic traits are passed down to the child. The process is iteratively done, which means that at the end of the generative succession, the criterion is reached and the solution to the given problem is produced.
## 2. Proposed Approaches
### Input/Output Format
In the application, the input is any picture and the picture format is narrowed into ”.jpg” or ”.png”. The output of the program is a set of pictures which illustrates the process of regenerating the given picture.
### Application Structure
The application is divided into 4 main parts.
• The initial part is the Setting class. In this class, we store the value of all variables to implement other classes in the application. 
• The second part is to draw and clone polygons. To draw or clone a polygon, we have implemented functions to add or delete points.
• The third part which is also the main part of the application is for calculating the DNA and evolving the image.
• The last part is Graphical User Interface and styling which contains of some FXML files for creating GUI, interacting input/output files and a CSS style sheet for styling.
