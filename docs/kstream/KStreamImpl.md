# KStreamImpl

`KStreamImpl` is a [KStream](KStream.md).

## <span id="doJoin"> doJoin

```java
KStream<K, VR> doJoin(
  KStream<K, VO> otherStream,
  ValueJoinerWithKey<? super K, ? super V, ? super VO, ? extends VR> joiner,
  JoinWindows windows,
  StreamJoined<K, V, VO> streamJoined,
  KStreamImplJoin join)
```

In the end, `doJoin` requests the given [KStreamImplJoin](KStreamImplJoin.md) to [join](KStreamImplJoin.md#join).

`doJoin` is used when:

* `KStreamImpl` is requested to [join](#join), [leftJoin](#leftJoin) and [outerJoin](#outerJoin)
