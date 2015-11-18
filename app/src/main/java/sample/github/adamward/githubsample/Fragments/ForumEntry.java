package sample.github.adamward.githubsample.Fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding.widget.RxTextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.functions.Func3;
import sample.github.adamward.githubsample.R;

import static android.text.TextUtils.isEmpty;
import static android.util.Patterns.EMAIL_ADDRESS;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForumEntry extends BaseFragment {

    @Bind(R.id.email_edit_text) EditText mEmailEditText;
    @Bind(R.id.pin_edit_text) EditText mPinEditText;
    @Bind(R.id.name_edit_text) EditText mNameEditText;
    @Bind(R.id.submit_forum_btn) Button mSubmitButton;
    View mRootLayout;


    private Observable<CharSequence> mEmailChangeObservable;
    private Observable<CharSequence> mPinChangeObservable;
    private Observable<CharSequence> mNameChangeObservable;

    private Subscription mTextSubscription;

    public ForumEntry() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootLayout = inflater.inflate(R.layout.fragment_forum_entry, container, false);
        ButterKnife.bind(this, mRootLayout);

        mEmailChangeObservable = RxTextView.textChanges(mEmailEditText).skip(1);
        mPinChangeObservable = RxTextView.textChanges(mPinEditText).skip(1);
        mNameChangeObservable = RxTextView.textChanges(mNameEditText).skip(1);

        combindEvents();

        return mRootLayout;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mTextSubscription != null) {
            mTextSubscription.unsubscribe();
        }
    }

    private void combindEvents() {
        mTextSubscription = Observable.combineLatest(mEmailChangeObservable, mPinChangeObservable, mNameChangeObservable,
                new Func3<CharSequence, CharSequence, CharSequence, Boolean>() {
                    @Override
                    public Boolean call(CharSequence emailSequence, CharSequence pinSequence, CharSequence nameSequence) {

                        boolean emailValid = !isEmpty(emailSequence) &&
                                EMAIL_ADDRESS.matcher(emailSequence).matches();
                        if (!emailValid) {
                            mEmailEditText.setError("Invalid Email!");
                        }

                        boolean pinValid = !isEmpty(pinSequence) && pinSequence.length() == 5;
                        if (!pinValid) {
                            mPinEditText.setError("Invalid Pin!");
                        }

                        boolean nameValid = !isEmpty(nameSequence) && nameSequence.length() >= 2;
                        if (!nameValid) {
                            mNameEditText.setError("Invalid Number!");
                        }

                        return emailValid && pinValid && nameValid;

                    }
                })
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Boolean validEntryCheck) {
                        mSubmitButton.setEnabled(validEntryCheck);
                    }
                });
    }

    @OnClick(R.id.submit_forum_btn)
    public void submit() {
        Snackbar.make(mRootLayout,"Forum submitted!",Snackbar.LENGTH_SHORT).show();
    }
}
