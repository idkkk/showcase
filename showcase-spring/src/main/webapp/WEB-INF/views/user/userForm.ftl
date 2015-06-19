<@layout.listView>
  <ol class="breadcrumb">
    <li><a href="#">首页</a></li>
    <li class="active">用户管理</li>
  </ol>
  <ul class="list-group">
  	<form action="${rc.getContextPath()}/user/update" method="post">
  	<input type="hidden" name="user.id" value="${user.id}" />
  	<input type="hidden" name="_method" value="put" />
    <li class="list-group-item">
      <label for="name">名称</label>
      <input type="text" name="user.name" value="${user.name}" class="form-control" />
    </li>
    <li class="list-group-item">
      <label for="city">城市</label>
      <input type="text" name="user.city" value="${user.city}" class="form-control" />
    </li>
    <li class="list-group-item">
      <label for="age">年龄</label>
      <input type="text" name="user.age" value="${user.age}" class="form-control" />
    </li>
    <li class="list-group-item">
      <label for="birthday">生日</label>
      <input type="text" name="user.birthday" value="${user.birthday}" class="form-control" />
    </li>
    <li class="list-group-item">
      <button type="submit" class="btn btn-default">提交</button>
      <button type="button" class="btn btn-default">返回</button>
    </li>
    </form>
  </ul>
</@layout.listView>
