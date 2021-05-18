package main

import "log"

// PublisherSubscriber is an interface that exposes the publish/subscribe method
// to be implemented by the EventManager
type PublisherSubscriber interface {
	subscribe(name string, handler func(args ...interface{}))
	publish(name string)
}

type EventManager struct {
	subscribers map[string][]func(args ...interface{})
}

func (ev *EventManager) subscribe(name string, handler func(args ...interface{})) {

	log.Printf("subscribe %s with handler %p", name, handler)

	ev.subscribers[name] = append(ev.subscribers[name], handler)
}

func (ev *EventManager) publish(name string, content []string) {

	log.Printf("<event> publish %s, subscribers %v", name, ev.subscribers[name])

	for _, f := range ev.subscribers[name] {
		f(content)
	}
}
