<@layout.listView>
  <ol class="breadcrumb">
    <li><a href="#">首页</a></li>
    <li class="active">用户管理</li>
  </ol>
  <div class="table-responsive">
    <table class="table table-striped table-bordered table-hover">
      <thead>
        <tr>
          <th>序号</th>
          <th>名称</th>
          <th>城市</th>
          <th>年龄</th>
          <th>生日</th>
        </tr>
      </thead>
      <tbody>
      	<#list users as user>
        <tr>
          <td>${user_index + 1}</td>
          <td>${user.name}</td>
          <td>${user.city}</td>
          <td>${user.age}</td>
          <td>${(user.birthday)?string("yyyy-MM-dd HH:mm:ss EEEE")}</td>
        </tr>
        </#list>
      </tbody>
    </table>
  </div>
</@layout.listView>
