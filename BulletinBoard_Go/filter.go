package main

import (
	"bytes"
	"io/ioutil"
	"log"
)

type Filter struct {
	Event    *EventManager
	Filepath string
	Marked   []string
}

func (f *Filter) load(args ...interface{}) {

	log.Printf("<execution> on Filter.load")

	data, err := ioutil.ReadFile(f.Filepath)

	if err != nil {
		log.Fatalf("couldn't read file %s, reason %v", f.Filepath, err)
	}

	if err != nil {
		log.Fatalf("couldn't read %s, reason %v", f.Filepath, err)
	}

	words := bytes.Split(data, []byte("\n"))

	for _, word := range words {
		f.Marked = append(f.Marked, string(word))
	}
}

func (f *Filter) filter(args ...interface{}) {

	log.Printf("<execution> on Filter.filter")

	var unfilteredWords, filteredWords []string

	for _, arg := range args {
		switch value := arg.(type) {
		case []string:
			unfilteredWords = value
		default:
			log.Printf("couldn't accept argument on Filter.filter")
			return
		}
	}

	for _, word := range unfilteredWords {
		if !f.isStopWord(word) {
			filteredWords = append(filteredWords, word)
		}
	}

	f.Event.publish("count", filteredWords)
}

func (f *Filter) isStopWord(word string) bool {

	if len(word) <= 3 {
		return true
	}

	for _, marked := range f.Marked {
		if word == marked {
			return true
		}
	}

	return false
}
