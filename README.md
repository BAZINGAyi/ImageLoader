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
 <hr>
 补充：项目中依赖导致原则的应用 DIP(Dependence Inversion Peinciple)
 概念：
 1. 高层模块不依赖于低层模块，两者都依赖于抽象(抽象指接口或抽象类，不能被直接实例化)
 2. 抽象不依赖于细节(细节为实现类，实现接口或者继承抽象而产生的就是细节)
 3. 细节应该依赖于抽象
 总结：高层模块为调用端，低层模块是具体实现类。模块间的依赖通过抽象产生，实现类之间不发生直接的依赖关系，依赖关系通过接口或抽象实现。
 具体体现：在之前的 ImageLoader 中 缓存是这样表示的：MemoryCache m = new MemoryCache()；这就导致了 ImageLoader 直接依赖了一个具体的实现，之后用户想用双缓存时，就需要改变成 DoubleCache c = new DoubleCache(); 当用户的需求再次发生改变的时候，还需要修改 ImageLoader ，但是应用上依赖倒置原则，将 ImageCache 抽象。就彻底解决了这个问题。如：ImageCache mCache = new MemoryCahe();在添加其他的缓存方式时，仅仅需要实现 ImageCahce 的接口。
 <hr>
 补充：项目中接口隔离原则的应用 InterfaceSegregation Principles
 概念：接口隔离的原则就是将庞大的接口拆分成更小的和更具体的接口
 应用：在之前的 DiskCache 缓存的 put 方法中必须要保证 *OutputStream*要被关闭，所以有了之复杂的写法，但是只要用到 *OutputStream* 就需要重复关闭的写法，
 带来的后果是代码可读性非常差。后来实现了 *CloseUtils* 工具类，现在只需要在相应的关闭操作内调用关闭即可.通过 Closeable 接口将可关闭的对象抽象起来
 ，这样只需要客户度啊依赖于 CLoseable 就可以对客户端隐藏其他接口信息，客户端只需要知道这个对象可关闭即可.
 总结：
 *ImageLoader* 中的 ImageCache 就是将接口隔离原则的运用，ImageLoader 只需要知道该缓存对象有存，取缓存的接口即可，其他的一概不管，这就使得缓存功能的具体实现对 ImageLoader 隐藏.

