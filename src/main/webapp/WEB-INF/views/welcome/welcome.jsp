<html>
<body>
    <h2>入力画面</h2>
    <form:form modelAttribute="welcomeForm">
        <div>メッセージ：<form:errors path="*" /></div>
        <div>
            <form:input path="id" placeholder="IDを入力してください"/>
            <form:errors path="id" />
            <br/>
            <form:input path="name" placeholder="名前を入力してください" maxlength="10"/>
            <form:errors path="name" />
        </div>
        <div>
            <form:button>確認</form:button>
        </div>
    </form:form>
</body>
</html>