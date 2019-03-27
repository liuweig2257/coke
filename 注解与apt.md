[定义一个注解，生成java文件](https://www.jianshu.com/p/07ef8ba80562)

## 元注解的种类预作用 ##

1. @Target  -->用来描述注解的作用范围
2. @Retention -->定义Anaotation存在时间的长短，也就是作用在什么时候，例如编译期，运行时
3. @Document -->标记注解，javadoc
4. @Inherited-->注解是否可以继承

## 自定义注解 ##

#### 基本格式 ####

	public @interface 注解名 {定义体}

使用@interface自定义注解时，自动继承了java.lang.annotation.Annotation接口，由编译程序自动完成其他细节。在定义注解时，不能继承其他的注解或接口。@interface用来声明一个注解，其中的每一个方法实际上是声明了一个配置参数。方法的名称就是参数的名称，返回值类型就是参数的类型（返回值类型只能是基本类型、Class、String、enum）。可以通过default来声明参数的默认值。

#### 参数 ####

1. 基本数据类型
2. class
3. enum
4. string
5. Annotation
6. 上面5中类型的数组

参数设定规则

1. 只能用public或默认(default)这两个访问权修饰.例如,String value();这里把方法设为defaul默认类型；　 　
2. 参数成员只能用基本类型byte,short,char,int,long,float,double,boolean八种基本数据类型和 String,Enum,Class,annotations等数据类型,以及这一些类型的数组.例如,String value();这里的参数成员就为String;　　
3. 如果只有一个参数成员,最好把参数名称设为"value",后加小括号.

	@Target(ElementType.FIELD)
	@Rentention(RetentionPolicy.RUNTIME)
	public @interface AnimalName{
		String value() default "";
	}

#### 几个主要的类与作用 ####

1. AbstractProcessor
2. ProcessorEnviroment
3. Elements

		public class MyProcessor extends AbstractProcessor
		{
			//必须存在，她会被注解处理工具调用。
			//它提供了工具类，types,Filer
		    @Override
		    public synchronized void init(ProcessingEnvironment processingEnvironment)
		    {
		        super.init(processingEnvironment);
		    }
		
			//相当于主方法，所有的操作都基于此
			//操作有 扫描，评估，处理注解代码，生成java文件的代码
		    @Override
		    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment)
		    {
		        return false;
		    }
			//定义该注解处理器注册到哪些注解上
		    @Override
		    public Set<String> getSupportedAnnotationTypes()
		    {
		        return super.getSupportedAnnotationTypes();
		    }
			//返回java的版本号
		    @Override
		    public SourceVersion getSupportedSourceVersion()
		    {
		        return super.getSupportedSourceVersion();
		    }
		}




Elements：一个用来处理Element的工具类，源代码的每一个部分都是一个特定类型的Element，例如

		package com.example;    // PackageElement

		public class Foo {        // TypeElement

    	private int a;      // VariableElement
    	private Foo other;  // VariableElement

    	public Foo () {}    // ExecuteableElement

    	public void setA (  // ExecuteableElement
                     	int newA   // TypeElement
                     	) {}
		}



