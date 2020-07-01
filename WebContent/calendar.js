/**
 * 
 */
const weeks = ['日', '月', '火', '水', '木', '金', '土']
const date = new Date()  //現在の日時を取得　constant=不変　Dateは誰でも使えるクラス
let year = date.getFullYear()
let month = date.getMonth() + 1
const config = {  //連想配列
    show: 1, //１ヶ月分のカレンダー表示
}

function showCalendar(year, month) { //カレンダーを表示するメソッド
    for ( i = 0; i < config.show; i++) {
        const calendarHtml = createCalendar(year, month) //戻り値calendarHtmlを代入
        const sec = document.createElement('section') //section機能を持つ変数の作成（HTMLの代わり）
        sec.innerHTML = calendarHtml  //sectionタグの中にcreateCalendarで作ったHTMLを挿入
        document.querySelector('#calendar').appendChild(sec) //HTMLからCalendarの要素を取得(指定したidを取得)
        								//appendChildでid=calendarの中にsectionを挿入
        
        month++ //現在の月が表示され +1されていく     翌月のｶﾚﾝﾀﾞｰの準備（複数月を表示の場合）
        if (month > 12) {   //12か月経つと1年増える
            year++
            month = 1
        }
    }   
}

//ｶﾚﾝﾀﾞｰの作成   HTMLも作成
function createCalendar(year, month) {
    const startDate = new Date(year, month - 1, 1) // 月の最初の日を取得   2020/6/1
    const endDate = new Date(year, month,  0) // 月の最後の日を取得   2020/6/30
    const endDayCount = endDate.getDate() // 月の末日   endDateから30を取り出している
    const lastMonthEndDate = new Date(year, month - 2, 0) // 前月の最後の日の情報
    const lastMonthendDayCount = lastMonthEndDate.getDate() // 前月の末日
    const startDay = startDate.getDay() // 月の最初の日の曜日を取得
    let dayCount = 1 // 日にちのカウント
    let calendarHtml = '' // HTMLを組み立てる変数

	var now = new Date();  //現在の日付をカレンダーに取得
	let dataFormat = 'YYYY/MM/DD';
	
	dataFormat = dataFormat.replace(/YYYY/g, now.getFullYear());
	dataFormat = dataFormat.replace(/MM/g, now.getMonth() + 1);
	dataFormat = dataFormat.replace(/DD/g, now.getDate());
	
	
	//選択された日にち 表示の変換
	let selectedDate = document.getElementById("selectedDate");
	let SpecificDate
	
	if(selectedDate){
		SpecificDate = selectedDate.innerText.replace(/-/g, "/");
	}
	

	//題名とｶﾚﾝﾀﾞｰのテーブルを指定
    calendarHtml += '<h1>' + year  + '/' + month + '</h1>'
    calendarHtml += '<table>'

    // 曜日の行を作成
    for (let i = 0; i < weeks.length; i++) {
        calendarHtml += '<td>' + weeks[i] + '</td>'
    }

    for (let w = 0; w < 6; w++) { 
        calendarHtml += '<tr>'

        for (let d = 0; d < 7; d++) {
            if (w == 0 && d < startDay) { //1日より前の余った日にちを表示
                // 1行目で1日の曜日の前
                let num = lastMonthendDayCount - startDay + d + 1  //カレンダーの縦
                calendarHtml += '<td class="is-disabled">' + num + '</td>'
            } else if (dayCount > endDayCount) {
                // 末尾の日数を超えた
                let num = dayCount - endDayCount
                calendarHtml += '<td class="is-disabled">' + num + '</td>'
                dayCount++
            } else { // 普通の日
            	let date = `${year}/${month}/${dayCount}`;   //表示方法　年/月/日
            	if(date == dataFormat) { // 本日の日付と対象の日付が一致する場合は背景色を付けるクラスを追加
            		calendarHtml += `<td class="calendar_td today" data-date="${year}/${month}/${dayCount}">${dayCount}</td>`
            	}else if(date == SpecificDate){
            		calendarHtml += `<td class="calendar_td specific" data-date="${year}/${month}/${dayCount}">${dayCount}</td>`
        			console.log(SpecificDate);
            	}else{
            		calendarHtml += `<td class="calendar_td" data-date="${year}/${month}/${dayCount}">${dayCount}</td>`
            	}
            
                dayCount++
          }
        }
        calendarHtml += '</tr>'  //曜日の区切り
    }
    calendarHtml += '</table>'  //縦の区切り

    return calendarHtml
}



function moveCalendar(e) {   //月変えるときのボタン　“e”イベント情報
    document.querySelector('#calendar').innerHTML = '' 
    	/*HTMLから要素を抽出
    	 * 一旦,HTMLのｶﾚﾝﾀﾞｰの中を消去
    	 *
    	 * */
    	
    	//前の月のｶﾚﾝﾀﾞｰの表示  
    if (e.target.id === 'prev') { //押されたボタンによっての処理(ターゲット)
        month--

        if (month < 1) {  //1月の時に先月を押したときに去年に戻る
            year--
            month = 12
        }
    }
    	//次の月のカレンダーの表示
    if (e.target.id === 'next') {
        month++

        if (month > 12) {  //12月の時に翌月を押したとき、来年に移る
            year++
            month = 1
        }
    }

    showCalendar(year, month) //カレンダーの再表示（一度カレンダーのを削除したため）
}


document.addEventListener("DOMContentLoaded", function(){  //イベントが発生した時に聞き手になる
	document.querySelector('#prev').addEventListener('click', moveCalendar)  //実際にボタンを押されたとき　処理を実装
	document.querySelector('#next').addEventListener('click', moveCalendar)

	document.addEventListener("click", function(e) {/*カレンダー上をクリックした時（日付など）*/
	    if(e.target.classList.contains("calendar_td")) {
	    	var spl = e.target.dataset.date.split('/'); /*択さたボタンを取得*/
	    	var year =spl[0];//年
	    	var month =spl[1];//月
	    	var day =spl[2];//日


	    	/* 取得した値をパラメータにセット(アンパサンド'&'で連結) */
	        var pram="year="+year+"&month="+month+"&day="+day;//getで使いたい
	        /* アドレスにパラメータを付加 */
	        location.href="SpecificDate?"+pram;//サーブレットを選択ここでサーブレットと連結
	        return false;
	    	
	    }
	})

	showCalendar(year, month); //showCalendarを実行
	

}, false);