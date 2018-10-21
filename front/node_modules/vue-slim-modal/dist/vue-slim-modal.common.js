'use strict';

function _interopDefault (ex) { return (ex && (typeof ex === 'object') && 'default' in ex) ? ex['default'] : ex; }

var _mergeJSXProps = _interopDefault(require('babel-helper-vue-jsx-merge-props'));
var assign = _interopDefault(require('object-assign'));

var index = {
  name: 'slim-modal',
  functional: true,
  props: {
    isOpen: {
      type: Boolean,
      required: true
    },
    clickOutside: {
      type: Function,
      default: function _default() {
        return function () {};
      }
    },
    isCenter: {
      type: Boolean,
      default: false
    },
    overlayStyle: {
      type: Object
    },
    overlayClass: {
      type: String
    }
  },
  render: function render(h, ctx) {
    var _ctx$props = ctx.props,
        isOpen = _ctx$props.isOpen,
        isCenter = _ctx$props.isCenter,
        clickOutside = _ctx$props.clickOutside,
        overlayClass = _ctx$props.overlayClass;

    if (!isOpen) return h();

    var contentStyles = isCenter ? assign({}, defaultStyles.content, {
      top: '50%',
      transform: transformCenter
    }) : defaultStyles.content;

    var overlayStyle = assign({}, defaultStyles.overlay, ctx.props.overlayStyle);

    return h(
      'div',
      { style: overlayStyle, 'class': overlayClass, on: {
          'click': clickOutside
        }
      },
      [h(
        'div',
        _mergeJSXProps([{ style: contentStyles, on: {
            'click': function click(e) {
              return e.stopPropagation();
            }
          }
        }, ctx.data]),
        [ctx.children]
      )]
    );
  }
};

var transformDefault = 'translateX(-50%)';
var transformCenter = 'translate(-50%, -50%)';
var defaultStyles = {
  overlay: {
    position: 'fixed',
    top: 0,
    left: 0,
    right: 0,
    bottom: 0,
    zIndex: 9990
  },
  content: {
    position: 'absolute',
    top: '100px',
    left: '50%',
    maxWidth: '80%',
    transform: transformDefault,
    border: '1px solid #e2e2e2',
    padding: '10px',
    backgroundColor: 'white',
    overflow: 'auto'
  }
};

module.exports = index;
