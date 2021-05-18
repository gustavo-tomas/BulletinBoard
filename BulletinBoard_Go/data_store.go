package main

import (
	"io/ioutil"
	"log"
	"strings"
)

type DataStorage struct {
	Event    *EventManager
	Filepath string
	Words    []string
}

func (d *DataStorage) load(args ...interface{}) {

	log.Printf("<execution> on DataStorage.load")

	data, err := ioutil.ReadFile(d.Filepath)

	if err != nil {
		log.Fatalf("couldn't read file %s, reason %v", d.Filepath, err)
	}

	text := string(data)

	parsed := strings.Split(text, "\n")

	// this complete string is used to remove punctuation marks and other kinds
	// of symbols. The way that this is done is quite inefficient.
	joined := strings.Join(parsed, " ")
	joined = strings.ReplaceAll(joined, ".", "")
	joined = strings.ReplaceAll(joined, ",", "")
	joined = strings.ReplaceAll(joined, "?", "")

	// contains all words and also blank spaces
	words := strings.Split(joined, " ")

	for _, word := range words {
		if len(word) > 0 {
			d.Words = append(d.Words, word)
		}
	}
}

func (d *DataStorage) produce(args ...interface{}) {
	log.Printf("<execution> on DataStorage.produce")
	d.Event.publish("filter", d.Words)
}