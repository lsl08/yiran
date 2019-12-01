(function(window) {
	var svgSprite = '<svg><symbol id="icon-xiangqing" viewBox="0 0 1024 1024"><path d="M513.717619 64.467267c-247.535723 0-448.205045 200.672392-448.205045 448.211185 0 247.536746 200.669322 448.205045 448.205045 448.205045 247.540839 0 448.211185-200.668299 448.211185-448.205045C961.928804 265.139659 761.258459 64.467267 513.717619 64.467267L513.717619 64.467267zM507.696486 834.880735c-22.99369 12.846586-41.802058 19.270902-56.388265 19.270902-12.787234 0-22.915919-3.931542-30.395264-11.776208-7.481392-7.848759-11.218506-18.738783-11.218506-32.654724 0-44.601824 27.373441-153.62589 82.125439-327.059917 2.559289-8.209986 3.837398-15.527648 3.837398-21.945825 0-7.131421-3.28686-10.704806-9.857509-10.704806-7.305383 0-15.600303 2.861164-24.904203 8.563026-9.31311 5.713119-30.039153 23.914666-62.147432 54.602595l-24.093744-18.201547c36.455283-39.250956 69.725014-67.184145 99.79589-83.773985 30.086226-16.590863 55.685253-24.8909 76.832899-24.8909 11.673877 0 20.60427 2.499937 26.800389 7.489578 6.191003 5.002944 9.299807 11.776208 9.299807 20.343327 0 10.353812-11.830443 54.248531-35.499515 131.687228-38.579667 126.679168-57.871035 203.057719-57.871035 229.101885 0 4.999874 1.269923 9.193383 3.832282 12.577456 2.552126 3.394307 5.2864 5.078669 8.214079 5.078669 11.67797 0 41.6107-22.116717 89.788979-66.372663l21.35333 20.340257C567.184345 792.586466 530.692223 822.033126 507.696486 834.880735L507.696486 834.880735zM635.065362 292.587801c-11.136641 11.962449-23.635303 17.928325-37.505195 17.928325-10.94733 0-20.076244-3.747347-27.376511-11.242042-7.300266-7.486508-10.94733-17.300015-10.94733-29.440519 0-16.053628 5.28333-29.707602 15.876596-40.950667 10.579963-11.235902 23.177885-16.854876 37.784558-16.854876 11.304464 0 20.61655 3.66139 27.916816 10.967796 7.300266 7.322779 10.946306 16.688077 10.946306 28.102035C651.764696 266.803555 646.19791 280.627399 635.065362 292.587801L635.065362 292.587801z"  ></path></symbol><symbol id="icon-warning" viewBox="0 0 1024 1024"><path d="M422.4 750.933333 422.4 750.933333 422.4 750.933333zM512 0C230.4 0 0 230.4 0 512s230.4 512 512 512 512-230.4 512-512S793.6 0 512 0zM512 960C264.533333 960 64 759.466667 64 512S264.533333 64 512 64 960 264.533333 960 512 759.466667 960 512 960zM477.866667 234.666667l72.533333 0 0 384-72.533333 0 0-384ZM477.866667 725.333333l72.533333 0 0 72.533333-72.533333 0 0-72.533333Z"  ></path></symbol><symbol id="icon-shanchushangwu103401" viewBox="0 0 1024 1024"><path d="M1011.2 134.4h-608c-3.2 0-3.2 0-6.4 3.2L3.2 502.4v3.2s0 3.2-3.2 3.2V518.4s0 3.2 3.2 3.2v3.2l393.6 364.8c3.2 3.2 3.2 3.2 6.4 3.2h608c6.4 0 12.8-3.2 12.8-12.8V147.2c0-9.6-3.2-12.8-12.8-12.8zM1001.6 512v352H409.6L28.8 512 409.6 160h592v352z"  ></path><path d="M499.2 665.6l144-140.8 140.8 140.8 16-16-140.8-140.8 140.8-144-16-16-140.8 144-144-144-16 16 144 144-144 140.8z"  ></path></symbol><symbol id="icon-dian" viewBox="0 0 1024 1024"><path d="M512 320a192.064 192.064 0 0 1 0 384 192 192 0 0 1 0-384z" fill="" ></path></symbol></svg>';
	var script = function() {
		var scripts = document.getElementsByTagName("script");
		return scripts[scripts.length - 1]
	}();
	var shouldInjectCss = script.getAttribute("data-injectcss");
	var ready = function(fn) {
		if(document.addEventListener) {
			if(~["complete", "loaded", "interactive"].indexOf(document.readyState)) {
				setTimeout(fn, 0)
			} else {
				var loadFn = function() {
					document.removeEventListener("DOMContentLoaded", loadFn, false);
					fn()
				};
				document.addEventListener("DOMContentLoaded", loadFn, false)
			}
		} else if(document.attachEvent) {
			IEContentLoaded(window, fn)
		}

		function IEContentLoaded(w, fn) {
			var d = w.document,
				done = false,
				init = function() {
					if(!done) {
						done = true;
						fn()
					}
				};
			var polling = function() {
				try {
					d.documentElement.doScroll("left")
				} catch(e) {
					setTimeout(polling, 50);
					return
				}
				init()
			};
			polling();
			d.onreadystatechange = function() {
				if(d.readyState == "complete") {
					d.onreadystatechange = null;
					init()
				}
			}
		}
	};
	var before = function(el, target) {
		target.parentNode.insertBefore(el, target)
	};
	var prepend = function(el, target) {
		if(target.firstChild) {
			before(el, target.firstChild)
		} else {
			target.appendChild(el)
		}
	};

	function appendSvg() {
		var div, svg;
		div = document.createElement("div");
		div.innerHTML = svgSprite;
		svgSprite = null;
		svg = div.getElementsByTagName("svg")[0];
		if(svg) {
			svg.setAttribute("aria-hidden", "true");
			svg.style.position = "absolute";
			svg.style.width = 0;
			svg.style.height = 0;
			svg.style.overflow = "hidden";
			prepend(svg, document.body)
		}
	}
	if(shouldInjectCss && !window.__iconfont__svg__cssinject__) {
		window.__iconfont__svg__cssinject__ = true;
		try {
			document.write("<style>.svgfont {display: inline-block;width: 1em;height: 1em;fill: currentColor;vertical-align: -0.1em;font-size:16px;}</style>")
		} catch(e) {
			console && console.log(e)
		}
	}
	ready(appendSvg)
})(window)