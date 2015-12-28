<#macro listView >
<!DOCTYPE html>
<html lang="zh">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>管理系统</title>
    <link href="${rc.getContextPath()}/assets/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="${rc.getContextPath()}/assets/styles/dashboard.css" rel="stylesheet">
  </head>
  <body>
    <#include "/templates/snippets/header.ftl">
    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
			<#include "/templates/snippets/sidebar.ftl">
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <#nested />
        </div>
      </div>
    </div>
    <script src="${rc.getContextPath()}/assets/jquery/1.11.1/jquery.min.js"></script>
    <script src="${rc.getContextPath()}/assets/bootstrap/3.3.0/js/bootstrap.min.js"></script>
  </body>
</html>
</#macro>