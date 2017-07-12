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
