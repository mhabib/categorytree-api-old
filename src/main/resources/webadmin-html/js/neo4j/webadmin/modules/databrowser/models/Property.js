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
  define(['ribcage/security/HtmlEscaper', 'ribcage/Model'], function(HtmlEscaper, Model) {
    var Property, htmlEscaper;
    htmlEscaper = new HtmlEscaper;
    return Property = (function() {
      __extends(Property, Model);
      function Property() {
        this.hasValueError = __bind(this.hasValueError, this);
        this.hasKeyError = __bind(this.hasKeyError, this);
        this.setKey = __bind(this.setKey, this);
        this.setValue = __bind(this.setValue, this);
        this.setValueError = __bind(this.setValueError, this);
        this.setKeyError = __bind(this.setKeyError, this);
        this.getKeyAsHtml = __bind(this.getKeyAsHtml, this);
        this.getTruncatedHtmlValue = __bind(this.getTruncatedHtmlValue, this);
        this.getValueAsHtml = __bind(this.getValueAsHtml, this);
        this.getValueAsJSON = __bind(this.getValueAsJSON, this);
        this.getKeyError = __bind(this.getKeyError, this);
        this.getValueError = __bind(this.getValueError, this);
        this.getValue = __bind(this.getValue, this);
        this.getKey = __bind(this.getKey, this);
        this.getLocalId = __bind(this.getLocalId, this);
        Property.__super__.constructor.apply(this, arguments);
      }
      Property.prototype.defaults = {
        key: "",
        value: "",
        keyError: false,
        valueError: false
      };
      Property.prototype.getLocalId = function() {
        return this.get("localId");
      };
      Property.prototype.getKey = function() {
        return this.get("key");
      };
      Property.prototype.getValue = function() {
        return this.get("value");
      };
      Property.prototype.getValueError = function() {
        return this.get("valueError");
      };
      Property.prototype.getKeyError = function() {
        return this.get("keyError");
      };
      Property.prototype.getValueAsJSON = function() {
        if (this.hasValueError()) {
          return this.getValue();
        } else {
          return JSON.stringify(this.getValue());
        }
      };
      Property.prototype.getValueAsHtml = function() {
        return htmlEscaper.escape(this.getValueAsJSON());
      };
      Property.prototype.getTruncatedHtmlValue = function(maxLength) {
        var str;
        if (maxLength == null) {
          maxLength = 100;
        }
        str = this.getValueAsJSON();
        if (str.length > maxLength) {
          str = str.substr(0, maxLength - 3) + "..";
        }
        return htmlEscaper.escape(str);
      };
      Property.prototype.getKeyAsHtml = function() {
        return htmlEscaper.escape(this.getKey());
      };
      Property.prototype.setKeyError = function(error) {
        return this.set({
          "keyError": error
        });
      };
      Property.prototype.setValueError = function(error) {
        return this.set({
          "valueError": error
        });
      };
      Property.prototype.setValue = function(value) {
        return this.set({
          "value": value
        });
      };
      Property.prototype.setKey = function(key) {
        return this.set({
          "key": key
        });
      };
      Property.prototype.hasKeyError = function() {
        return this.getKeyError() !== false;
      };
      Property.prototype.hasValueError = function() {
        return this.getValueError() !== false;
      };
      return Property;
    })();
  });
}).call(this);
