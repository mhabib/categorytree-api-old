(function() {
  /*
  Copyright (c) 2002-2013 "Neo Technology,"
  Network Engine for Objects in Lund AB [http://neotechnology.com]
  
  This file is part of Neo4j.
  
  Neo4j is free software: you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.
  
  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.
  
  You should have received a copy of the GNU General Public License
  along with this program.  If not, see <http://www.gnu.org/licenses/>.
  */
  var __bind = function(fn, me){ return function(){ return fn.apply(me, arguments); }; }, __hasProp = Object.prototype.hasOwnProperty, __extends = function(child, parent) {
    for (var key in parent) { if (__hasProp.call(parent, key)) child[key] = parent[key]; }
    function ctor() { this.constructor = child; }
    ctor.prototype = parent.prototype;
    child.prototype = new ctor;
    child.__super__ = parent.prototype;
    return child;
  };
  define(['./base', './DashboardInfoView', './DashboardChartsView', 'ribcage/View', 'lib/amd/jQuery'], function(template, DashboardInfoView, DashboardChartsView, View, $) {
    var DashboardView;
    return DashboardView = (function() {
      __extends(DashboardView, View);
      function DashboardView() {
        this.detach = __bind(this.detach, this);
        this.remove = __bind(this.remove, this);
        this.render = __bind(this.render, this);
        this.initialize = __bind(this.initialize, this);
        DashboardView.__super__.constructor.apply(this, arguments);
      }
      DashboardView.prototype.template = template;
      DashboardView.prototype.initialize = function(opts) {
        this.opts = opts;
        this.appState = opts.state;
        this.server = this.appState.getServer();
        this.kernelBean = opts.kernelBean;
        return this.kernelBean.bind("change", this.render);
      };
      DashboardView.prototype.render = function() {
        var kernelVersion;
        kernelVersion = this.kernelBean.get("kernel") != null ? this.kernelBean.get("kernel").KernelVersion : "N/A";
        $(this.el).html(this.template({
          server: {
            url: this.server.url,
            version: kernelVersion
          }
        }));
        if (this.infoView != null) {
          this.infoView.remove();
        }
        if (this.chartsView != null) {
          this.chartsView.remove();
        }
        this.infoView = new DashboardInfoView(this.opts);
        this.chartsView = new DashboardChartsView(this.opts);
        this.infoView.attach($("#dashboard-info", this.el));
        this.chartsView.attach($("#dashboard-charts", this.el));
        this.infoView.render();
        this.chartsView.render();
        return this;
      };
      DashboardView.prototype.remove = function() {
        this.kernelBean.unbind("change", this.render);
        this.infoView.remove();
        this.chartsView.remove();
        return DashboardView.__super__.remove.call(this);
      };
      DashboardView.prototype.detach = function() {
        this.chartsView.unbind();
        return DashboardView.__super__.detach.call(this);
      };
      return DashboardView;
    })();
  });
}).call(this);
