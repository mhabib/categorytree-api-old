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
  define(['ribcage/Model'], function(Model) {
    var DashboardState;
    return DashboardState = (function() {
      __extends(DashboardState, Model);
      function DashboardState() {
        this.setChart = __bind(this.setChart, this);
        this.setChartByKey = __bind(this.setChartByKey, this);
        this.setZoomLevel = __bind(this.setZoomLevel, this);
        this.setZoomLevelByKey = __bind(this.setZoomLevelByKey, this);
        this.getZoomLevelKey = __bind(this.getZoomLevelKey, this);
        this.getZoomLevel = __bind(this.getZoomLevel, this);
        this.getChartKey = __bind(this.getChartKey, this);
        this.getChart = __bind(this.getChart, this);
        this.initialize = __bind(this.initialize, this);
        DashboardState.__super__.constructor.apply(this, arguments);
      }
      DashboardState.prototype.charts = {
        primitives: {
          layers: [
            {
              label: "Nodes",
              key: 'node_count'
            }, {
              label: "Properties",
              key: 'property_count'
            }, {
              label: "Relationships",
              key: 'relationship_count'
            }
          ],
          chartSettings: {
            yaxis: {
              min: 0
            }
          }
        },
        usageRequests: {
          layers: [
            {
              label: "Count",
              key: 'request_count'
            }
          ],
          chartSettings: {
            yaxis: {
              min: 0
            }
          }
        },
        usageTimes: {
          layers: [
            {
              label: "Time Mean",
              key: 'request_mean_time'
            }, {
              label: "Time Median",
              key: 'request_median_time'
            }, {
              label: "Time Max",
              key: 'request_max_time'
            }, {
              label: "Time Min",
              key: 'request_min_time'
            }
          ],
          chartSettings: {
            yaxis: {
              min: 0
            }
          }
        },
        usageBytes: {
          layers: [
            {
              label: "Bytes",
              key: 'request_bytes'
            }
          ],
          chartSettings: {
            yaxis: {
              min: 0
            }
          }
        },
        memory: {
          layers: [
            {
              label: "Memory usage",
              key: 'memory_usage_percent',
              lines: {
                show: true,
                fill: true,
                fillColor: "#4f848f"
              }
            }
          ],
          chartSettings: {
            yaxis: {
              min: 0,
              max: 100
            },
            tooltipYFormatter: function(v) {
              return Math.floor(v) + "%";
            }
          }
        }
      };
      DashboardState.prototype.zoomLevels = {
        year: {
          xSpan: 60 * 60 * 24 * 365,
          granularity: 60 * 60 * 24 * 12,
          timeformat: "%d/%m %y"
        },
        month: {
          xSpan: 60 * 60 * 24 * 30,
          granularity: 60 * 60 * 24,
          timeformat: "%d/%m"
        },
        week: {
          xSpan: 60 * 60 * 24 * 7,
          granularity: 60 * 60 * 6,
          timeformat: "%d/%m"
        },
        day: {
          xSpan: 60 * 60 * 24,
          granularity: 60 * 48,
          timeformat: "%H:%M"
        },
        six_hours: {
          xSpan: 60 * 60 * 6,
          granularity: 60 * 12,
          timeformat: "%H:%M"
        },
        thirty_minutes: {
          xSpan: 60 * 30,
          granularity: 60,
          timeformat: "%H:%M"
        }
      };
      DashboardState.prototype.initialize = function(options) {
        this.setChartByKey("primitives");
        return this.setZoomLevelByKey("six_hours");
      };
      DashboardState.prototype.getChart = function(key) {
        return this.get("chart" + key);
      };
      DashboardState.prototype.getChartKey = function() {
        return this.get("chartKey");
      };
      DashboardState.prototype.getZoomLevel = function() {
        return this.get("zoomLevel");
      };
      DashboardState.prototype.getZoomLevelKey = function() {
        return this.get("zoomLevelKey");
      };
      DashboardState.prototype.setZoomLevelByKey = function(key) {
        this.set({
          "zoomLevelKey": key
        });
        return this.setZoomLevel(this.zoomLevels[key]);
      };
      DashboardState.prototype.setZoomLevel = function(zl) {
        return this.set({
          zoomLevel: zl
        });
      };
      DashboardState.prototype.setChartByKey = function(key) {
        this.set({
          "chartKey": key
        });
        return this.setChart(key, this.charts[key]);
      };
      DashboardState.prototype.setChart = function(key, chart) {
        var tmp;
        tmp = {};
        tmp["chart" + key] = chart;
        return this.set(tmp);
      };
      return DashboardState;
    })();
  });
}).call(this);
