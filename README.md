- **MVP架构 / MVVM架构 / MVI架构**

- Kotlin

- 沉浸式状态栏

  https://blog.csdn.net/qq_34681580/article/details/103955191

- 屏幕适配

    - ~~宽高限定符适配~~（过时

      在资源文件下生成不同分辨率的资源文件，然后在布局文件中引用对应的 dimens

    - **今日头条屏幕适配框架**

      https://github.com/JessYanCoding/AndroidAutoSize

      以一个分辨率为基准，适配其他屏幕，ps：该分辨率一般为UI设计分辨率

    - **smallestWidth框架**

- **BaseRecyclerViewAdapterHelper**

  最强RecyclerView框架，自带空白页、上拉加载、下拉刷新等

  https://github.com/CymChad/BaseRecyclerViewAdapterHelper

- **Retrofit & RxJava** **联合网络请求**

  https://juejin.cn/post/6844903881521004552

- **MMKV**

  微信自研，是基于 mmap 内存映射的 key-value 组件，性能以及稳定性高，是Android **Sharedpreferences **上级替代，用于存储数据

  https://github.com/Tencent/MMKV

- **QMUI_Android**

  腾讯开源Android UI框架，设计目的是用于辅助快速搭建一个具备基本设计还原效果的 Android 项目，同时利用自身提供的丰富控件及兼容处理，让开发者能专注于业务需求而无需耗费精力在基础代码的设计上。不管是新项目的创建，或是已有项目的维护，均可使开发效率和项目质量得到大幅度提升。

  https://github.com/Tencent/QMUI_Android

- Tinker

  微信Android热补丁方案

  https://github.com/Tencent/tinker

- ARouter

  阿里开源Android路由跳转

  https://github.com/alibaba/ARouter

- 组件化

  https://juejin.cn/post/7124120644521820190

- Lottie

  Lottie 是 [Airbnb](https://link.juejin.cn?target=https%3A%2F%2Fgithub.com%2Fairbnb) 开源的一套跨平台的完整的动画效果解决方案，设计师可以使用 [Adobe After Effects](https://link.juejin.cn?target=https%3A%2F%2Fwww.adobe.com%2Fcn%2Fproducts%2Faftereffects.html) 设计出漂亮的动画之后，使用 `Lottic` 提供的 [Bodymovin](https://link.juejin.cn?target=https%3A%2F%2Fgithub.com%2Fairbnb%2Flottie-web) 插件将设计好的动画导出成 JSON 格式，就可以直接运用在 `iOS`、`Android`、`Web` 和 `React Native`之上，无需其他额外操作。

  https://lottiefiles.com/

- 犸良

  https://design.alipay.com/emotion/

  https://lordicon.com/

- CountDownTimer 计时器

- Banner轮播图

  https://github.com/youth5201314/banner


## ps

- 项目/模块使用Kotlin需要在build.gradle 添加

  ```
  apply plugin: 'kotlin-android'
  apply plugin: 'kotlin-android-extensions'
  ```