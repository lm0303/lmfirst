## 插入式注解器实现

进入到java目录下依次执行以下命令:

```cmd
javac com/example/jvm/NameChecker.java -encoding utf-8
javac com/example/jvm/NameCheckProcessor.java -encoding utf-8
javac -processor com.example.jvm.NameCheckProcessor com/example/jvm/BADLY_NAMED_CODE.java
```

![image](https://user-images.githubusercontent.com/37624070/115209749-7ba75c80-a130-11eb-97e4-73e5b120b374.png)