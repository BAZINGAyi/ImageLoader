# ImageLoader
修改1:
使用单一职责原则对 ImageLoader 进行修改
修改后类图如下：
  ![image](https://github.com/BAZINGAyi/ImageLoader/raw/master/screenshot/singleObligation.png)
<hr/>
修改后存在的问题：
1. 通过内存缓存虽然解决了每次从网路上加载图片的问题，但是由于设备的内存有限，每当应用重新启动后，原来加载的图片需要重新下载，所以考虑引入 SD 卡缓存功能。
<hr/>
修改 1.
根据开闭原则，通过 ImageLoader 中的 setImaageCache() 方法注入不同的缓存实现，提高了扩展性
开闭原则：对扩展开放，对修改是封闭。
修改后类图如下：
  ![image](https://github.com/BAZINGAyi/ImageLoader/raw/master/screenshot/openCloseObligation.png)
 <hr/>
 补充：
 重构后对里式替换原则的说明：
 概念：只要父类能出现的地方，子类就能出现，而且替换为子类不会产生任何问题。(核心：抽象（抽象又依赖于继承）)
 项目中 MemoryCache、DiskCache 都可以替换 ImageCache 的工作，并且保证行为的正确性，用户只要在使用时指定具体的缓存对象就可以动态替换 ImageCache 中的缓存策略。