# KStreamImplJoin

## Creating Instance

`KStreamImplJoin` takes the following to be created:

* <span id="builder"> [InternalStreamsBuilder](InternalStreamsBuilder.md)
* <span id="leftOuter"> `leftOuter` flag
* <span id="rightOuter"> `rightOuter` flag

`KStreamImplJoin` is created when:

* `KStreamImpl` is requested to [join](KStreamImpl.md#join), [leftJoin](KStreamImpl.md#leftJoin) and [outerJoin](KStreamImpl.md#outerJoin)

## <span id="join"> join

```java
KStream<K1, R> join(
  KStream<K1, V1> lhs,
  KStream<K1, V2> other,
  ValueJoinerWithKey<? super K1, ? super V1, ? super V2, ? extends R> joiner,
  JoinWindows windows,
  StreamJoined<K1, V1, V2> streamJoined)
```

`join`...FIXME

`join` is used when:

* `KStreamImpl` is requested to [doJoin](KStreamImpl.md#doJoin)
