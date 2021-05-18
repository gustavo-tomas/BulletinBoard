package main

import (
	"log"
	"sort"
)

// Pair is a structure used to abstract a key and value
type Pair struct {
	Key   string
	Value int
}

// Pairs is an abstraction of a map such as this structure allow sorting by
// implementing the sort.Interface
type Pairs []Pair

// Words is an entity of the system
type Words struct {
	Event     *EventManager
	Frequency map[string]int
}

func (w *Words) count(args ...interface{}) {

	log.Printf("<execution> on Words.count")

	var names []string

	for _, arg := range args {
		switch value := arg.(type) {
		case []string:
			names = value
		default:
			log.Printf("couldn't accept argument on Words.count")
			return
		}
	}

	w.Frequency = make(map[string]int)

	for _, name := range names {
		w.Frequency[name]++
	}
}

func (w *Words) print(args ...interface{}) {

	log.Printf("<execution> on Words.print")

	pairs := make(Pairs, len(w.Frequency))
	i := 0
	for k, v := range w.Frequency {
		pairs[i] = Pair{Key: k, Value: v}
		i++
	}

	sort.Sort(pairs)

	// print only the 25 most frequent messages
	for i, p := range pairs {
		if i < 25 {
			log.Printf("%s %d", p.Key, p.Value)
		}
	}
}

func (p Pairs) Len() int           { return len(p) }
func (p Pairs) Less(i, j int) bool { return p[i].Value > p[j].Value }
func (p Pairs) Swap(i, j int)      { p[i], p[j] = p[j], p[i] }
