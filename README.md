# NineGridLayout
九宫格布局，可自定义item的布局

Step 1. Add the JitPack repository to your build file

gradle
maven
sbt
leiningen
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://www.jitpack.io' }
		}
	}
Step 2. Add the dependency

  从1.0.1版本开始，版本号前不加v，引用时需要注意。

	dependencies {
	        implementation 'com.github.goweii:NineGridLayout:1.0.1'
	}
