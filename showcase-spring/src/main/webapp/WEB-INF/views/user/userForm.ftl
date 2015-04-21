<@layout.listView>
  <ol class="breadcrumb">
    <li><a href="#">首页</a></li>
    <li class="active">用户管理</li>
  </ol>
  <ul class="list-group">
  	<input type="hidden" id="id" value="${user.id}">
    <li class="list-group-item">
      <label for="name">名称</label>
      <input type="text" class="form-control" id="name" value="${user.name}" readonly>
    </li>
    <li class="list-group-item">
      <label for="city">城市</label>
      <input type="text" class="form-control" id="city" value="${user.city}" readonly>
    </li>
    <li class="list-group-item">
      <label for="age">年龄</label>
      <input type="text" class="form-control" id="age" value="${user.age}" readonly>
    </li>
    <li class="list-group-item">
      <label for="birthday">生日</label>
      <input type="text" class="form-control" id="birthday" value="${(user.birthday)?string("yyyy-MM-dd")}" readonly>
    </li>
    <li class="list-group-item">
      <button type="button" class="btn btn-default">返回</button>
    </li>
  </ul>
</@layout.listView>
