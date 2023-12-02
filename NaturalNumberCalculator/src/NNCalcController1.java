import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * Controller class.
 *
 * @author Put your name here
 */
public final class NNCalcController1 implements NNCalcController {

    /**
     * Model object.
     */
    private final NNCalcModel model;

    /**
     * View object.
     */
    private final NNCalcView view;

    /**
     * Useful constants.
     */
    private static final NaturalNumber TWO = new NaturalNumber2(2),
            INT_LIMIT = new NaturalNumber2(Integer.MAX_VALUE);

    /**
     * Updates this.view to display this.model, and to allow only operations
     * that are legal given this.model.
     *
     * @param model
     *            the model
     * @param view
     *            the view
     * @ensures [view has been updated to be consistent with model]
     */
    private static void updateViewToMatchModel(NNCalcModel model,
            NNCalcView view) {

        NaturalNumber top = model.top();
        NaturalNumber bottom = model.bottom();

        view.updateTopDisplay(top);
        view.updateBottomDisplay(bottom);

        view.updateDivideAllowed(!bottom.isZero());
        view.updateSubtractAllowed(top.compareTo(bottom) >= 0);
        view.updatePowerAllowed(bottom.compareTo(INT_LIMIT) < 0);
        view.updateRootAllowed(bottom.compareTo(TWO) >= 0);
    }

    /**
     * Constructor.
     *
     * @param model
     *            model to connect to
     * @param view
     *            view to connect to
     */
    public NNCalcController1(NNCalcModel model, NNCalcView view) {
        this.model = model;
        this.view = view;
        updateViewToMatchModel(model, view);
    }

    /**
     * Processes event to clear bottom operand.
     *
     * @updates this.model.bottom, this.view
     * @ensures <pre>
     * this.model.bottom = 0  and
     * [this.view has been updated to match this.model]
     * </pre>
     */
    @Override
    public void processClearEvent() {
        /*
         * Get alias to bottom from model
         */
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        bottom.clear();
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    /**
     * Processes event to swap operands.
     *
     * @updates this.model, this.view
     * @ensures <pre>
     * this.model.top = #this.model.bottom  and
     * this.model.bottom = #this.model.top  and
     * [this.view has been updated to match this.model]
     * </pre>
     */
    @Override
    public void processSwapEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        NaturalNumber temp = top.newInstance();
        temp.transferFrom(top);
        top.transferFrom(bottom);
        bottom.transferFrom(temp);
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    /**
     * Processes event to enter bottom operand to top.
     *
     * @updates this.model.top, this.view
     * @ensures <pre>
     * this.model.top = this.model.bottom  and
     * [this.view has been updated to match this.model]
     * </pre>
     */
    @Override
    public void processEnterEvent() {
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();

        top.copyFrom(bottom);
        updateViewToMatchModel(this.model, this.view);
    }

    /**
     * Processes event to do an add operation.
     *
     * @updates this.model, this.view
     * @ensures <pre>
     * this.model.top = 0  and
     * this.model.bottom = #this.model.top + #this.model.bottom  and
     * [this.view has been updated to match this.model]
     * </pre>
     */
    @Override
    public void processAddEvent() {
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();

        top.add(bottom);
        bottom.transferFrom(top);

        updateViewToMatchModel(this.model, this.view);
    }

    /**
     * Processes event to do a subtract operation.
     *
     * @updates this.model, this.view
     * @requires this.model.bottom <= this.model.top
     * @ensures <pre>
     * this.model.top = 0  and
     * this.model.bottom = #this.model.top - #this.model.bottom  and
     * [this.view has been updated to match this.model]
     * </pre>
     */
    @Override
    public void processSubtractEvent() {
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();

        // top should be bigger than bottom
        if (top.compareTo(bottom) >= 0) {
            top.subtract(bottom);
            bottom.transferFrom(top);
        }

        updateViewToMatchModel(this.model, this.view);
    }

    /**
     * Processes event to do a multiply operation.
     *
     * @updates this.model, this.view
     * @ensures <pre>
     * this.mode.top = 0  and
     * this.model.bottom = #this.model.top * #this.model.bottom  and
     * [this.view has been updated to match this.model]
     * </pre>
     */
    @Override
    public void processMultiplyEvent() {
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();

        top.multiply(bottom);
        bottom.transferFrom(top);

        updateViewToMatchModel(this.model, this.view);
    }

    /**
     * Processes event to do a divide operation.
     *
     * @updates this.model, this.view
     * @requires this.model.bottom > 0
     * @ensures <pre>
     * #this.model.top =
     *   this.model.bottom * #this.model.bottom + this.model.top  and
     * 0 <= this.model.top < #this.model.bottom  and
     * [this.view has been updated to match this.model]
     * </pre>
     */
    @Override
    public void processDivideEvent() {
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();

        NaturalNumber remainder = top.divide(bottom);
        top.transferFrom(remainder);

        bottom.transferFrom(top);

        updateViewToMatchModel(this.model, this.view);
    }

    /**
     * Processes event to do a power operation.
     *
     * @updates this.model, this.view
     * @requires this.model.bottom <= INT_LIMIT
     * @ensures <pre>
     * this.model.top = 0  and
     * this.model.bottom = #this.model.top ^ (#this.model.bottom)  and
     * [this.view has been updated to match this.model]
     * </pre>
     */
    @Override
    public void processPowerEvent() {
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();

        // The power should be power < INT_LIMIT
        if (bottom.compareTo(INT_LIMIT) < 0) {
            top.power(bottom.toInt());
            bottom.transferFrom(top);
        }

        updateViewToMatchModel(this.model, this.view);
    }

    /**
     * Processes event to do a root operation.
     *
     * @updates this.model, this.view
     * @requires 2 <= this.model.bottom <= INT_LIMIT
     * @ensures <pre>
     * this.model.top = 0  and
     * this.model.bottom =
     *   [the floor of the #this.model.bottom root of #this.model.top]  and
     * [this.view has been updated to match this.model]
     * </pre>
     */
    @Override
    public void processRootEvent() {
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();

        // The power should be 2 <= power < INT_LIMIT
        if (bottom.compareTo(INT_LIMIT) < 0 && bottom.compareTo(TWO) >= 0) {
            top.root(bottom.toInt());
            bottom.transferFrom(top);
        }

        updateViewToMatchModel(this.model, this.view);
    }

    /**
     * Processes event to add a new (low-order) digit to the bottom operand.
     *
     * @param digit
     *            the low-order digit to be added
     *
     * @updates this.model.bottom, this.view
     * @requires 0 <= digit < 10
     * @ensures <pre>
     * this.model.bottom = #this.model.bottom * 10 + digit  and
     * [this.view has been updated to match this.model]
     * </pre>
     */
    @Override
    public void processAddNewDigitEvent(int digit) {
        NaturalNumber bottom = this.model.bottom();

        // Prevent adding digits after 0
        if (bottom.isZero()) {
            bottom.setFromInt(digit);
        } else {
            String temp = bottom.toString();
            temp += digit;
            bottom.setFromString(temp);
        }

        updateViewToMatchModel(this.model, this.view);
    }

}
