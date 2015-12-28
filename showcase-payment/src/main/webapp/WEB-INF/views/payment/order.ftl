<@layout.listView>
  <ol class="breadcrumb">
    <li><a href="#">首页</a></li>
    <li class="active">订单管理</li>
  </ol>
  <ul class="list-group">
    <li class="list-group-item">
      <label for="name">订单号</label>
      <input type="text" name="user.name" value="${data.orderId}" class="form-control" />
    </li>
    <li class="list-group-item">
      <label for="city">订单金额</label>
      <input type="text" name="user.city" value="${data.orderAmount}" class="form-control" />
    </li>
    <li class="list-group-item">
      <label for="age">下单时间</label>
      <input type="text" name="user.age" value="${data.orderTime}" class="form-control" />
    </li>
    <li class="list-group-item">
      <label for="birthday">商品名称</label>
      <input type="text" name="user.birthday" value="${data.productName}" class="form-control" />
    </li>
    <li class="list-group-item">
      <label for="birthday">商品数量</label>
      <input type="text" name="user.birthday" value="${data.productNum}" class="form-control" />
    </li>
  	<form name="kqPay" action="${data.payGateway}" method="post">
        <input type="hidden" name="inputCharset" value="${data.inputCharset}" />
        <input type="hidden" name="pageUrl" value="${data.pageUrl}" />
        <input type="hidden" name="bgUrl" value="${data.bgUrl}" />
        <input type="hidden" name="version" value="${data.version}" />
        <input type="hidden" name="language" value="${data.language}" />
        <input type="hidden" name="signType" value="${data.signType}" />
        <input type="hidden" name="signMsg" value="${data.signMsg}" />
        <input type="hidden" name="merchantAcctId" value="${data.merchantAcctId}" />
        <input type="hidden" name="payerName" value="${data.payerName}" />
        <input type="hidden" name="payerContactType" value="${data.payerContactType}" />
        <input type="hidden" name="payerContact" value="${data.payerContact}" />
        <input type="hidden" name="orderId" value="${data.orderId}" />
        <input type="hidden" name="orderAmount" value="${data.orderAmount}" />
        <input type="hidden" name="orderTime" value="${data.orderTime}" />
        <input type="hidden" name="productName" value="${data.productName}" />
        <input type="hidden" name="productNum" value="${data.productNum}" />
        <input type="hidden" name="productId" value="${data.productId}" />
        <input type="hidden" name="productDesc" value="${data.productDesc}" />
        <input type="hidden" name="payType" value="${data.payType}" />
        <li class="list-group-item">
          <button type="submit" class="btn btn-default">提交</button>
        </li>
    </form>
  </ul>
</@layout.listView>
