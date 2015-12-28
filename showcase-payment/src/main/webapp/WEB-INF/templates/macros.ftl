<#-- string strip -->
<#macro cutContent param len><#if param?length lt len>${param}<#else><@stringStripDirective text=param length=len ext="..." /></#if></#macro>

<#-- page number -->
<#macro showPageNumbers leftBoundry rightBoundry currentPageNumer>
<#list leftBoundry..rightBoundry as index>
    <#if (index == currentPageNumer)>
        <a class="down">${index}</a>
    <#else>
        <a href="<@lefeng.urlReplace "pageNo=${index}" "" />#clothList">${index}</a>
    </#if>
</#list>
</#macro>

<#-- toolbar for page -->
<#macro pageableBar result>
<#if (result.totalPages > 1)>
<div class="pages ClearFix">
    <span>
        <#if (result.number == 1)>
            <a class="uppage stop">&lt;上一页</a>
        <#else>
            <a class="uppage" href="<@lefeng.urlReplace "pageNo=${result.number - 1}" "" />#clothList">&lt;上一页</a>
        </#if>

        <#if (result.totalPages <= 10)>
            <@lefeng.showPageNumbers 1 result.totalPages result.number />
        <#else>
            <#if (result.number <= 5)>
                <@lefeng.showPageNumbers 1 10 result.number/>
                <#if (result.totalPages <= 12)>
                    <@lefeng.showPageNumbers 11 12 result.number/>
                <#else>
                    <em>...</em>
                    <@lefeng.showPageNumbers result.totalPages-1 result.totalPages result.number/>
                </#if>
            <#else>
                <@lefeng.showPageNumbers 1 2 result.number/>
                <em>...</em>
                <#if ((result.number+9) >= result.totalPages)>
                    <#if ((result.totalPages - result.number) < 7)>
                    	<@lefeng.showPageNumbers result.totalPages-9 result.totalPages result.number/>
                    <#else>
                        <@lefeng.showPageNumbers result.number-2 result.totalPages result.number/>
                    </#if>
                <#else>
                    <@lefeng.showPageNumbers result.number-2 result.number+7 result.number/>
                    <em>...</em>
                    <@lefeng.showPageNumbers result.totalPages-1 result.totalPages result.number/>
                </#if>
            </#if>
        </#if>
        <#if (result.number == result.totalPages)>
            <a class="downpage stop">下一页&gt;</a>
        <#else>
            <a class="downpage" href="<@lefeng.urlReplace "pageNo=${result.number + 1}" "" />#clothList">下一页&gt;</a>
        </#if>
    </span>
</div>
</#if>
</#macro>