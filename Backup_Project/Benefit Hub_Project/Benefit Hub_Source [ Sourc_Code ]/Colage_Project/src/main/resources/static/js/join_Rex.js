			function sub() {
	    		if (Rex()) {
	        		document.forms[0].submit();
	    		}
			}
		    function Rex() {
		      var username = document.getElementsByName('username')[0].value;
		      var password = document.getElementsByName('password')[0].value;
		      var u_nm = document.getElementsByName('u_nm')[0].value;
		      var u_addr = document.getElementsByName('u_addr')[0].value;
		      var u_sec_n1 = document.getElementsByName('u_sec_n1')[0].value;
		      var u_sec_n2 = document.getElementsByName('u_sec_n2')[0].value;

		      var username_Rex = /^[a-zA-Z0-9]{5,20}$/;
		      var password_Rex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,20}$/;
		      var u_nm_Rex = /^[가-힣]{2,4}$/;
		      var u_addr_Rex = /^.{5,100}$/;
		      var u_sec_n1_Rex = /^[0-9]{6}$/;
		      var u_sec_n2_Rex = /^[0-9]{7}$/;

		      if (!username__Rex.test(username)) {
		        alert("아이디는 영문 대소문자 및 숫자로 입력해주세요.");
		        return false;
		      }

		      if (!password_Rex.test(password)) {
		        alert("비밀번호는 8자 이상, 최소 하나의 대소문자 및 숫자를 포함해야 합니다.");
		        return false;
		      }

		      if (!u_nm_Rex.test(u_nm)) {
		        alert("이름은 한글로 입력해주세요");
		        return false;
		      }

		      if (!u_addr_Rex.test(u_addr)) {
		        alert("주소를 올바르게 입력해주세요");
		        return false;
		      }

		      if (!u_sec_n1_Rex.test(u_sec_n1) || !u_sec_n2_Rex.test(u_sec_n2)) {
		        alert("주민번호를 올바르게 입력해주세요");
		        return false;
		      }

		      return true;
		    }