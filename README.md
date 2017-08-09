# TimeLineView
[![](https://www.jitpack.io/v/zlisa/TimeLineView.svg)](https://www.jitpack.io/#zlisa/TimeLineView)

> 自定义时间轴

Mark和依赖者水平对齐：
```
app:rely="@id/xx"
```
隐藏头线和尾线：
```
hideStartLine(boolean)
hideEndLine(boolean)
```
![image](https://github.com/zlisa/TimeLineView/raw/master/images/Screenshot01.png)

> 自定义属性

```
    <declare-styleable name="TimeLineView">
        <!--被依赖者-->
        <attr name="rely" format="reference" />
        <!--是否被标记-->
        <attr name="is_marked" format="boolean" />
        <!--标记颜色-->
        <attr name="color" format="color" />
        <!--未标记颜色-->
        <attr name="second_color" format="color" />
        <!--标记圆半径-->
        <attr name="mark_radius" format="dimension" />
        <!--连接线宽度-->
        <attr name="line_width" format="dimension" />
        <!--连接线和标记间隔-->
        <attr name="line_padding" format="dimension" />
    </declare-styleable>
```
