# Simple Search Engine

This is a simple command-line search engine program written in Kotlin. It allows users to search for names or emails in a dataset and retrieve matching results based on different search strategies.

## Features

- **Search Strategies**: The search engine supports three search strategies: `ALL`, `ANY`, and `NONE`.
- **Data Input**: The program reads data from a text file specified as a command-line argument.
- **Menu Interface**: Users can interact with the program through a menu interface to perform various operations.

## Search Strategies**:

- **ALL**: Retrieves lines containing all words from the query.
- **ANY**: Retrieves lines containing any of the words from the query.
- **NONE**: Retrieves lines that do not contain any of the words from the query.

## Data format**:
The data file should contain lines of text, each representing a person's information. Names and emails should be separated by spaces or punctuation.
Example data file (data.txt):

Katie Jacobs katie@example.com
Erick Harrington erick@example.com
Myrtle Medina myrtle@example.com
Erick Burgess erick@example.com
