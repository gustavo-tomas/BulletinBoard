package main

import (
	"flag"
	"log"
	"path"
)

var (
	ev EventManager

	filepath = flag.String("filepath", "resources",
		"the filepath from where to read resources")
)

func main() {
	flag.Parse()

	subs := make(map[string][]func(args ...interface{}))

	ev = EventManager{
		subscribers: subs,
	}

	data := DataStorage{Event: &ev, Filepath: path.Join(*filepath, "words.txt")}
	filter := Filter{
		Event:    &ev,
		Filepath: path.Join(*filepath, "stop_words.txt"),
	}
	word := Words{Event: &ev}

	data.Event.subscribe("load", data.load)
	filter.Event.subscribe("load", filter.load)
	filter.Event.subscribe("filter", filter.filter)

	data.Event.subscribe("produce", data.produce)

	word.Event.subscribe("count", word.count)
	word.Event.subscribe("print", word.print)

	log.Printf("there are %d subscribers", len(ev.subscribers))

	// Assuming this method is the main entity that publishes events in the
	// board
	ev.publish("load", []string{})
	ev.publish("produce", []string{})
	ev.publish("print", []string{})
}
