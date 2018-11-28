# DemoIPC

#### 使用不同方式实现IPC

#### Android中的IPC方式
-使用Bundle  
-使用文件共享  
-使用Messenger  
-使用AIDL  
-使用ContentProvider  
-使用Socket  

### Binder是什么？
- Binder是一种跨进程的通信方式
- 对Server来说，Binder是一个本地对象
- 对Client来说，Binder是一个代理对象
- 对传输过程来说，Binder是一种可以跨进程传递的对象，Binder驱动会对具有跨进程传递能力的对象进行特殊处理，自动完成代理对象和本地对象的转换
- Binder可以理解为一种虚拟物理设备，它的驱动是/dev/binder
- Binder是Android中的一个类，它实现了IBinder接口
- 从Android Framework角度看，Binder是ServiceManager连接各种Manger（ActivityManger WindowManger等）和MangerService的桥梁
### Binder机制是如何跨进程的？
Server中有一个Binder本地对象，Client通过Binder驱动向Server请求Binder对象，Binder驱动将本地对象转换为代理对象返回给Client，Client通过操作代理对象与Server中的本地对象通信

![avatar](https://github.com/liuhe37186/DemoIPC/blob/f216dce634cc1a934d1b7339d6cbc6eec9a3d12c/app/src/main/assets/Binder%E6%9C%BA%E5%88%B61.png)
![avatar](https://github.com/liuhe37186/DemoIPC/blob/HEAD/app/src/main/assets/Binder%E9%A9%B1%E5%8A%A8.png)

