# ProcessorNodePunctuator

`ProcessorNodePunctuator` is an [abstraction](#contract) of [processor punctuators](#implementations) (that [PunctuationQueue](../PunctuationQueue.md)s use to [mayPunctuate](../PunctuationQueue.md#mayPunctuate)).

## Contract

### <span id="punctuate"> punctuate

```java
void punctuate(
  ProcessorNode<?, ?, ?, ?> node,
  long timestamp,
  PunctuationType type,
  Punctuator punctuator)
```

Used when:

* `PunctuationQueue` is requested to [mayPunctuate](../PunctuationQueue.md#mayPunctuate)

## Implementations

* [StreamTask](../StreamTask.md)
