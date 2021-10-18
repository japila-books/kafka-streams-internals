# AbstractStream

## <span id="ensureCopartitionWith"> ensureCopartitionWith

```java
Set<String> ensureCopartitionWith(
  Collection<? extends AbstractStream<K, ?>> otherStreams)
```

`ensureCopartitionWith`...FIXME

`ensureCopartitionWith` is used when:

* `CogroupedStreamAggregateBuilder` is requested to `processRepartitions`
* `KStreamImpl` is requested to [doJoin](KStreamImpl.md#doJoin) and [doStreamTableJoin](KStreamImpl.md#doStreamTableJoin)
* `KTableImpl` is requested to [doJoin](KTableImpl.md#doJoin)
