<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2022/9/26
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
  <title>购物车页面</title>
  <link rel="stylesheet" href="css/fullCar.css" />
  <link rel="stylesheet" href="css/common.css"/>
  <link rel="stylesheet" href="css/style.css" />
  <link rel="stylesheet" href="css/icons.css" />
  <link rel="stylesheet" href="css/table.css" />
  <link rel="stylesheet" type="text/css" href="css/shopCar.css">
</head>
<body>
<!--顶部-->
<div class="top">
  <div class="top_center">
    <ul class="top_bars">
      <li><a href="/loginout">退出</a>|</li>
      <li><a href="#">我的订单<span class="jt_down"></span></a>|</li>
      <li><a href="#">关注杰普<span class="jt_down"></span></a>|</li>
      <li><a href="#">网站导航<span class="jt_down"></span></a></li>
    </ul>
  </div>
</div>
<!--头部-->
<div class="header3">
  <a href="#"><img src="images/logo.png"  class="oneImg"></a>


  <div class="h3_right">
    <img src="images/play_03.png" alt="">
  </div>

</div>
<!--中间部分div-->
<div class="empty">
  <div class="peisong"><pre>全部商品  ${shopCars.size()} </pre></div>
  <div class="mainCenter">
    <div class="allCheck">
      <input type="checkbox"><p>全选</p>
      <p class="leftM">商品</p>
      <p class="rightM">单价(元)</p>
      <p class="leftM">数量</p>
      <p class="leftM">小计(元)</p>
      <p class="leftM">操作</p>
    </div>
    <div class="mainPro">
      <div class="aa">
        <input type="checkbox"><span id="but">自营</span>
      </div>
<%--      循环显示购物车信息--%>
      <c:forEach items="${shopCars}" var="shopCars" step="1" varStatus="vs">
        <form action="/shopCarUpdate" method="post">
          <table>
            <tbody>
            <tr>
              <td class="index">${vs.count}</td>
              <td>
                <img src="${shopCars.book.image}" width="80px" height="100px" >
                <span>
                                    ${shopCars.book.name}
                                    <br>
                                </span>
              </td>
              <td>
                <span>¥${shopCars.book.price}</span>
              </td>
              <td class="index2">
                <input type="hidden" name="bookId" value="${shopCars.book.id}">
                <input type="text" name="num" value="${shopCars.num}"><br>
                <span>有货</span>
              </td>
              <td>
<%--                小计--%>
                <span>¥${shopCars.book.price*shopCars.num}</span>
                <c:set var="total" value="${total+shopCars.book.price*shopCars.num}"></c:set>
              </td>
              <td >
                <button type="submit">保存</button>
                <button><a href="/shopCarDelete?bookId=${shopCars.book.id}">取消</a></button>
              </td>
            </tr>
            </tbody>
          </table>
        </form>
      </c:forEach>

      <div class="allButtom">
        <!-- <p class="caozuo">去结算</p> -->
        <!-- <input value="去提交" class="caozuo" type="submit"> -->
        <a href="/shopcarcommit" class="caozuo">去提交</a>
        <span>已选择<font>${shopCars.size()}</font>件商品&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;总价(不含运费)：<font>¥${total}元</font></span>
      </div>
    </div>


  </div>

</div>



<!--脚部-->
<div class="footer3">
  <div class="f3_top">
    <div class="f3_center">
      <div class="ts1">品目繁多 愉悦购物</div>
      <div class="ts2">正品保障 天天低价</div>
      <div class="ts3">极速物流 特色定制</div>
      <div class="ts4">优质服务 以客为尊</div>
    </div>
  </div>
  <div class="f3_middle">
    <ul class="f3_center">
      <li class="f3_mi_li01">
        <h1>购物指南</h1>
        <p>常见问题</p>
        <p>找回密码</p>
        <p>会员介绍</p>
        <p>购物演示</p>
      </li>
      <li class="f3_mi_li01">
        <h1>配送方式</h1>
        <p>常见问题</p>
        <p>找回密码</p>
        <p>会员介绍</p>
        <p>购物演示</p>
      </li>
      <li class="f3_mi_li01">
        <h1>支付方式</h1>
        <p>常见问题</p>
        <p>找回密码</p>
        <p>会员介绍</p>
        <p>购物演示</p>
      </li>
      <li class="f3_mi_li01">
        <h1>售后服务</h1>
        <p>常见问题</p>
        <p>找回密码</p>
        <p>会员介绍</p>
        <p>购物演示</p>
      </li>
      <li class="f3_mi_li01">
        <h1>服务保障</h1>
        <p>常见问题</p>
        <p>找回密码</p>
        <p>会员介绍</p>
        <p>购物演示</p>
      </li>
      <li class="f3_mi_li06">
        <h1>客服中心</h1>
        <img src="images/qrcode_jprj.jpg" width="80px" height="80px">
        <p>抢红包、疑问咨询、优惠活动</p>
      </li>
    </ul>
  </div>
  <div class="f3_bottom">
    <p class="f3_links">
      <a href="#">关于我们</a>|
      <a href="#">联系我们</a>|
      <a href="#">友情链接</a>|
      <a href="#">供货商入驻</a>
    </p>
    <p>沪ICP备14033591号-8 杰普软件briup.com版权所有 杰普软件科技有限公司 </p>
    <img src="images/police.png">
  </div>
</div>
</body>
</html>
