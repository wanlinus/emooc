function scoreFun(object, opts) {
    // 默认属性
    let defaults = {
        fen_d: 16,  // 每个a的宽度
        ScoreGrade: 10,  // a的个数
        types: [
            "这老师讲的什么鬼啊",
            "这老师讲的什么鬼啊",
            "老师讲课我居然在打瞌睡",
            "老师讲课我居然在打瞌睡",
            "老师讲得一般",
            "老师讲得一般",
            "老师讲得不错",
            "老师讲得不错",
            "老师讲得非常好",
            "老师讲得非常好"
        ],
        nameScore: "fenshu",
        parent: "star_score",
        attitude: "attitude"
    };
    options = $.extend({}, defaults, opts);
    let countScore = object.find("." + options.nameScore);  // 找到名为“fenshu”的类
    let startParent = object.find("." + options.parent);    // 找到名为“star_score”的类
    let atti = object.find("." + options.attitude);    // 找到名为“attitude”的类
    let now_cli;
    let fen_cli;
    let atu;
    let fen_d = options.fen_d;     // 每个a的宽度
    let len = options.ScoreGrade;  // 把a的个数赋值给len
    startParent.width(fen_d * len); //包含a的div盒子的宽度
    let preA = (5 / len);
    for (let i = 0; i < len; i++) {
        let newSpan = $("<a href='javascript:void(0)'></a>");     // 不整体刷新页面的情况下，可以使用void(0)
        newSpan.css({"left": 0, "width": fen_d * (i + 1), "z-index": len - i});  // 设置a的宽度、层级
        newSpan.appendTo(startParent)
    }                                    //  把a放到类名为“star_score”的div里
    startParent.find("a").each(          // each（）方法
        function (index, element) {
            $(this).click(function () {        // 点击事件
                now_cli = index;                   // 当前a的索引值
                show(index, $(this))             //  调用show方法
            });
            $(this).mouseenter(function () {    /* mouseenter事件(与 mouseover 事件不同，只有在鼠标指针穿过被选元素时，
		  	                                 才会触发 mouseenter 事件。如果鼠标指针穿过任何子元素，同样会触发 mouseover 事件。) */
                show(index, $(this))
            });
            $(this).mouseleave(function () {    // mouseleave事件
                if (now_cli >= 0) {
                    let scor = preA * (parseInt(now_cli) + 1);         // 评分
                    startParent.find("a").removeClass("clibg");  // 清除a的“clibg”类
                    startParent.find("a").eq(now_cli).addClass("clibg"); // eq()选择器，选取索引值为“now_cli”的a，给它加上“clibg”类
                    let ww = fen_d * (parseInt(now_cli) + 1);                  // 当前a的宽度
                    startParent.find("a").eq(now_cli).css({"width": ww, "left": "0"});  // 给索引值为“now_cli”的a加上宽度“ww”和left值
                    if (countScore) {
                        countScore.text(scor)
                    }
                } else {
                    startParent.find("a").removeClass("clibg");
                    if (countScore) {
                        countScore.text("")
                    }
                }
            })
        });

    // show方法
    function show(num, obj) {
        let n = parseInt(num) + 1;
        let lefta = num * fen_d;
        let ww = fen_d * n;
        let scor = preA * n;                        // 评分
        (len > 5) ? atu = options.types[parseInt(num)] : atu = options.types[parseInt(num) + 10];       // 用户态度
        object.find("a").removeClass("clibg");  // 清除所有a的“clibg”类
        obj.addClass("clibg");                  // 给当前a添加“clibg”类
        obj.css({"width": ww, "left": "0"});       // 给当前a添加宽度“ww”和left值
        countScore.text(scor);                  // 显示评分
        atti.text(atu);                        // 显示用户态度
    }
};