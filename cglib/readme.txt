1.代理为控制要访问的目标对象提供了一种途径。当访问对象时，它引入了一个间接的层。
JDK自从1.3版本开始就引入了动态代理，并且经常被用来动态地创建代理。
JDK的动态代理用起来非常简单，但它有一个限制，就是使用动态代理的对象必须实现一个或多个接口。
如果想代理没有实现接口的继承的类，该怎么 办？
可以使用CGLIB包
CGLIB是一个强大的高性能的代码生成包。它广泛的被许多AOP的框架使用，例如Spring AOP和dynaop，
为它们提供方法的interception（拦截）。
最流行的OR Mapping工具hibernate也使用CGLIB来代理单端single-ended(多对一和一对一)关联（对集合的延迟抓取，是采用其他机制实 现的）。
EasyMock和jMock是通过使用模仿（moke）对象来测试java代码的包。它们都通过使用CGLIB来为那些没有接口的类创建模仿 （moke）对象。

CGLIB包的底层是通过使用一个小而快的字节码处理框架ASM，来转换字节码并生成新的类。
除了CGLIB包，脚本语言例如 Groovy和BeanShell，也是使用ASM来生成java的字节码。
但不鼓励直接使用ASM，因为它要求必须对JVM内部结构包括class文件的格式和指令集都很熟悉。

Hiberater使用JDK的动态代理实现一个专门为 WebShere应用服务器的事务管理适配器；
Spring AOP，如果不强制使用CGLIB包，默认情况是使用JDK的动态代理来代理接口。

2.CGLIB代理的APIS
net.sf.cglib.core
底层字节码处理类，它们大部分与ASM有关系。
net.sf.cglib.transform
编译期或运行期类和类文件的转换
net.sf.cglib.proxy
实现创建代理和方法拦截器的类
net.sf.cglib.reflect
实现快速反射和C#风格代理的类
net.sf.cglib.util
集合排序工具类
net.sf.cglib.beans
JavaBean相关的工具类
大多时候，仅仅为了动态地创建代理，仅需要使用到代理包中很少的一些API。

CGLIB包是在ASM之上的一个高级别的层。对代理那些没有实现接口的类非常有用。

*本质上，它是通过动态的生成一个子类去覆盖所要代理类的不是final的方法，并设置好callback，
对原有类的每个方法调用就会转变成调用用户定义的拦截方法（interceptors），这比 JDK动态代理方法快多。

net.sf.cglib.proxy.Callback接口在CGLIB包中是一个很关键的接口，
所有被net.sf.cglib.proxy.Enhancer类调用的回调（callback）接口都要继承这个接口。
net.sf.cglib.proxy.MethodInterceptor接口是最通用的回调（callback）类型，
它经常被基于代理的AOP用来实现拦截（intercept）方法的调用。这个接口只定义了一个方法
public Object intercept(Object object, java.lang.reflect.Method method,
Object[] args, MethodProxy proxy) throws Throwable;

当net.sf.cglib.proxy.MethodInterceptor做为所有代理方法的回调 （callback）时，
当对基于代理的方法调用时，在调用原对象的方法的之前会调用这个方法，
第一个参数是代理对像，第二和第三个参数分别是拦截的方法和方法的参数。
原来的方法可能通过使用java.lang.reflect.Method对象的一般反射调用，
或者使用 net.sf.cglib.proxy.MethodProxy对象调用。
net.sf.cglib.proxy.MethodProxy通常被首选使用，因为它更快。
在这个方法中，可以在调用原方法之前或之后注入自己的代码。
 
net.sf.cglib.proxy.MethodInterceptor能够满足任何的拦截（interception ）需要，
为了简化和提高性能，CGLIB包提供了一些专门的回调（callback）类型。例如：
net.sf.cglib.proxy.FixedValue 
为提高性能，FixedValue回调对强制某一特别方法返回固定值是有用的。
net.sf.cglib.proxy.NoOp 
NoOp回调把对方法调用直接委派到这个方法在父类中的实现。
net.sf.cglib.proxy.LazyLoader 
当实际的对象需要延迟装载时，可以使用LazyLoader回调。一旦实际对象被装载，它将被每一个调用代理对象的方法使用。
net.sf.cglib.proxy.Dispatcher 
Dispathcer回调和LazyLoader回调有相同的特点，不同的是，当代理方法被调用时，装载对象的方法也总要被调用。
net.sf.cglib.proxy.ProxyRefDispatcher 
ProxyRefDispatcher回调和Dispatcher一样，不同的是，它可以把代理对象作为装载对象方法的一个参数传递。

代理类的所以方法经常会用到回调（callback），也可以使用net.sf.cglib.proxy.CallbackFilter有选择的对一些方法使用回调（callback），
这种考虑周详的控制特性在JDK的动态代理中是没有的。
在JDK代理中，对 java.lang.reflect.InvocationHandler方法的调用对代理类的所以方法都有效。

CGLIB的代理包也对net.sf.cglib.proxy.Mixin提供支持。
基本上，它允许多个对象被绑定到一个单个的大对象。在代理中对方法的调用委托到下面相应的对象中。
